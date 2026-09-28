package com.legatech.legabail.service.controle;

import com.legatech.legabail.entity.Regle;
import com.legatech.legabail.repository.RegleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoteurReglesService {

    private final RegleRepository regleRepository;

    public MoteurReglesService(RegleRepository regleRepository) {
        this.regleRepository = regleRepository;
    }

    @Transactional(readOnly = true)
    public List<ResultatControle> verifier(DonneesLocation donnees) {
        List<ResultatControle> resultats = new ArrayList<>();
        List<Regle> regles = regleRepository.findAll();

        for (Regle regle : regles) {
            switch (regle.getCode()) {
                case "ART1_R2_LOGEMENTS_D_HABITATION" ->
                        ajouterSiPresent(resultats, verifierTypeLogement(regle, donnees));

                case "ART1_R3_HOTELS_EXCLUS" ->
                        ajouterSiPresent(resultats, verifierHotelOuPension(regle, donnees));

                case "ART1_R4_CERTAINS_LOCAUX_PROFESSIONNELS" ->
                        ajouterSiPresent(resultats, verifierUsageCommercialIndustriel(regle, donnees));

                case "ART1_R6_LOGEMENTS_DESTINES_AU_PERSONNEL" ->
                        ajouterSiPresent(resultats, verifierLogementLieEmploi(regle, donnees));

                case "ART3_R1_LIBERTE_PENDANT_LES_CINQ_PREMIERES_ANNEES" ->
                        ajouterSiPresent(resultats, verifierDatePermisHabiter(regle, donnees));

                case "ART3_R2_PLAFOND_APRES_CINQ_ANS" ->
                        ajouterSiPresent(resultats, verifierAncienneteBatiment(regle, donnees));

                case "ART6_R1_LOGEMENT_CONCERNE" ->
                        ajouterSiPresent(resultats, verifierLoyer(regle, donnees));

                case "ART6_R2_LOCATION_FAITE_AU_MOIS" ->
                        ajouterSiPresent(resultats, verifierCautionEtAvance(regle, donnees));

                case "ART6_R3_AUTRES_TYPES_DE_LOCATION" ->
                        ajouterSiPresent(resultats, verifierCharges(regle, donnees));

                case "ART7_R1_MAJORATION_MAXIMALE" ->
                        ajouterSiPresent(resultats, verifierMajorationMeuble(regle, donnees));

                case "ART7_R3_CONDITION_POUR_APPLIQUER_LE_MAXIMUM" ->
                        ajouterSiPresent(resultats, verifierInventaireMeuble(regle, donnees));

                case "ART10_R2_SOUS_LOCATION_DE_PLUS_DES_DEUX_TIERS" ->
                        ajouterSiPresent(resultats, verifierSousLocation(regle, donnees));

                case "ART16_R1_FIN_AUTOMATIQUE" ->
                        ajouterSiPresent(resultats, verifierDureeDeterminee(regle, donnees));

                case "ART16_R3_TRANSFORMATION_DU_BAIL" ->
                        ajouterSiPresent(resultats, verifierDureeIndeterminee(regle, donnees));

                default -> {
                    // La règle existe en base, mais elle n'est pas encore automatisée dans ce moteur.
                }
            }
        }

        return resultats;
    }

    @Transactional(readOnly = true)
    public boolean peutEnregistrer(DonneesLocation donnees) {
        return verifier(donnees).stream()
                .noneMatch(resultat -> !resultat.conforme() && resultat.bloquante());
    }

    private ResultatControle verifierTypeLogement(Regle regle, DonneesLocation donnees) {
        boolean conforme = donnees.getTypeLogement() != null;

        return construireResultat(
                regle,
                conforme,
                "Le type de logement doit être précisé : logement nu ou logement meublé."
        );
    }

    private ResultatControle verifierHotelOuPension(Regle regle, DonneesLocation donnees) {
        boolean conforme = !donnees.isHotelOuPension();

        return construireResultat(
                regle,
                conforme,
                "Les hôtels et pensions ne sont pas traités comme un bail d'habitation classique."
        );
    }

    private ResultatControle verifierUsageCommercialIndustriel(Regle regle, DonneesLocation donnees) {
        if (donnees.getUsage() == null) {
            return construireResultat(
                    regle,
                    false,
                    "L'usage du logement doit être précisé."
            );
        }

        boolean conforme = donnees.getUsage() != DonneesLocation.Usage.COMMERCIAL_INDUSTRIEL;

        return construireResultat(
                regle,
                conforme,
                "Le bail d'habitation ne doit pas être utilisé pour un usage commercial ou industriel."
        );
    }

    private ResultatControle verifierLogementLieEmploi(Regle regle, DonneesLocation donnees) {
        boolean conforme = !donnees.isLogementLieEmploi();

        return construireResultat(
                regle,
                conforme,
                "Un logement directement lié à un emploi peut dépendre de règles particulières."
        );
    }

    private ResultatControle verifierDatePermisHabiter(Regle regle, DonneesLocation donnees) {
        boolean conforme = donnees.getDatePermisHabiter() != null;

        return construireResultat(
                regle,
                conforme,
                "La date du permis d'habiter doit être indiquée pour connaître l'ancienneté du bâtiment."
        );
    }

    private ResultatControle verifierAncienneteBatiment(Regle regle, DonneesLocation donnees) {
        if (donnees.getDatePermisHabiter() == null) {
            return null;
        }

        int ageBatiment = Period.between(donnees.getDatePermisHabiter(), LocalDate.now()).getYears();
        boolean conforme = ageBatiment < 5;

        return construireResultat(
                regle,
                conforme,
                "Le bâtiment a 5 ans ou plus. Le loyer peut être soumis aux règles de plafond prévues par les articles sur les anciens bâtiments."
        );
    }

    private ResultatControle verifierLoyer(Regle regle, DonneesLocation donnees) {
        boolean conforme = donnees.getLoyer() != null
                && donnees.getLoyer().compareTo(BigDecimal.ZERO) > 0;

        return construireResultat(
                regle,
                conforme,
                "Le montant du loyer doit être renseigné et supérieur à zéro."
        );
    }

    private ResultatControle verifierCharges(Regle regle, DonneesLocation donnees) {
        boolean conforme = donnees.getCharges() == null
                || donnees.getCharges().compareTo(BigDecimal.ZERO) >= 0;

        return construireResultat(
                regle,
                conforme,
                "Les charges mensuelles ne peuvent pas être négatives."
        );
    }

    private ResultatControle verifierCautionEtAvance(Regle regle, DonneesLocation donnees) {
        if (donnees.getTypeLogement() != DonneesLocation.TypeLogement.NU) {
            return null;
        }

        if (donnees.getLoyer() == null || donnees.getCaution() == null || donnees.getAvance() == null) {
            return construireResultat(
                    regle,
                    false,
                    "Pour vérifier cette règle, le loyer, la caution et l'avance doivent être renseignés."
            );
        }

        BigDecimal totalCautionAvance = donnees.getCaution().add(donnees.getAvance());
        BigDecimal plafond = donnees.getLoyer().multiply(new BigDecimal("2"));
        boolean conforme = totalCautionAvance.compareTo(plafond) <= 0;

        return construireResultat(
                regle,
                conforme,
                "Pour un logement nu payé au mois, la caution et l'avance ne doivent pas dépasser deux mois de loyer."
        );
    }

    private ResultatControle verifierMajorationMeuble(Regle regle, DonneesLocation donnees) {
        if (donnees.getTypeLogement() != DonneesLocation.TypeLogement.MEUBLE) {
            return null;
        }

        if (donnees.getPrixLogementNu() == null || donnees.getLoyer() == null) {
            return construireResultat(
                    regle,
                    false,
                    "Pour vérifier le loyer meublé, il faut indiquer le prix du même logement non meublé."
            );
        }

        BigDecimal plafondMeuble = donnees.getPrixLogementNu().multiply(new BigDecimal("1.5"));
        boolean conforme = donnees.getLoyer().compareTo(plafondMeuble) <= 0;

        return construireResultat(
                regle,
                conforme,
                "Le loyer d'un logement meublé ne doit pas dépasser 50% de plus que le prix du même logement non meublé."
        );
    }

    private ResultatControle verifierInventaireMeuble(Regle regle, DonneesLocation donnees) {
        if (donnees.getTypeLogement() != DonneesLocation.TypeLogement.MEUBLE) {
            return null;
        }

        return construireResultat(
                regle,
                donnees.isInventaireFourni(),
                "Pour un logement meublé, l'inventaire des meubles doit être fourni."
        );
    }

    private ResultatControle verifierSousLocation(Regle regle, DonneesLocation donnees) {
        if (donnees.getSousLocation() == null) {
            return construireResultat(
                    regle,
                    false,
                    "La situation de sous-location doit être précisée."
            );
        }

        boolean conforme = donnees.getSousLocation()
                != DonneesLocation.SousLocation.DEMANDEE_SANS_AUTORISATION;

        return construireResultat(
                regle,
                conforme,
                "La sous-location ne peut pas être faite librement sans respecter les conditions prévues."
        );
    }

    private ResultatControle verifierDureeDeterminee(Regle regle, DonneesLocation donnees) {
        if (donnees.getTypeDuree() != DonneesLocation.TypeDuree.DETERMINEE) {
            return null;
        }

        boolean conforme = donnees.getDateDebut() != null
                && donnees.getDateFin() != null
                && donnees.getDateFin().isAfter(donnees.getDateDebut());

        return construireResultat(
                regle,
                conforme,
                "Pour un contrat à durée déterminée, la date de fin doit être après la date de début."
        );
    }

    private ResultatControle verifierDureeIndeterminee(Regle regle, DonneesLocation donnees) {
        if (donnees.getTypeDuree() != DonneesLocation.TypeDuree.INDETERMINEE) {
            return null;
        }

        boolean conforme = donnees.getDateDebut() != null && donnees.getDateFin() == null;

        return construireResultat(
                regle,
                conforme,
                "Pour un contrat à durée indéterminée, il faut une date de début, mais pas de date de fin obligatoire."
        );
    }

    private ResultatControle construireResultat(Regle regle, boolean conforme, String messageSiErreur) {
        String article = "Article " + regle.getArticleJuridique().getNumero();
        String message = conforme ? regle.getExplicationSimple() : messageSiErreur;

        return new ResultatControle(
                regle.getCode(),
                article,
                conforme,
                regle.isBloquante(),
                message
        );
    }

    private void ajouterSiPresent(List<ResultatControle> resultats, ResultatControle resultat) {
        if (resultat != null) {
            resultats.add(resultat);
        }
    }
}

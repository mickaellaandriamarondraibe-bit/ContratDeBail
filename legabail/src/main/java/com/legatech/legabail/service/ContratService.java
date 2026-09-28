package com.legatech.legabail.service;

import com.legatech.legabail.entity.Bien;
import com.legatech.legabail.entity.Candidature;
import com.legatech.legabail.entity.Contrat;
import com.legatech.legabail.entity.Proposition;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.repository.CandidatureRepository;
import com.legatech.legabail.repository.ContratRepository;
import com.legatech.legabail.repository.PropositionRepository;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ContratService {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ContratRepository contrats;
    private final CandidatureRepository candidatures;
    private final PropositionRepository propositions;

    public ContratService(ContratRepository contrats, CandidatureRepository candidatures,
                          PropositionRepository propositions) {
        this.contrats = contrats;
        this.candidatures = candidatures;
        this.propositions = propositions;
    }

    public Contrat generer(Proposition reference) {
        Long candidatureId = propositions.trouverCandidatureId(reference.getId()).orElseThrow();
        Candidature candidature = candidatures.verrouiller(candidatureId).orElseThrow();
        Proposition proposition = propositions
                .findFirstByCandidatureIdOrderByNumeroVersionDesc(candidature.getId()).orElseThrow();
        if (!proposition.getId().equals(reference.getId())
                || !proposition.isBailleurAccepte() || !proposition.isLocataireAccepte()) {
            throw new IllegalArgumentException(
                    "Le contrat exige le double accord sur la derniere proposition.");
        }
        return contrats.findByPropositionId(proposition.getId()).orElseGet(() -> {
            Contrat contrat = new Contrat();
            contrat.setProposition(proposition);
            contrat.setNumero(String.format("LB-%d-%04d", Year.now().getValue(), proposition.getId()));
            contrat.setContenu(construireContenu(proposition));
            candidature.setStatut("ACCEPTEE");
            return contrats.save(contrat);
        });
    }

    @Transactional(readOnly = true)
    public Contrat consulter(Long id, Long utilisateurId) {
        Contrat contrat = contrats.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        PartiesContrat.verifier(contrat.getProposition().getCandidature(), utilisateurId);
        PartiesContrat.charger(contrat.getProposition().getCandidature());
        return contrat;
    }

    private String construireContenu(Proposition proposition) {
        Candidature candidature = proposition.getCandidature();
        Bien bien = candidature.getAnnonce().getBien();
        String fin = proposition.getDateFin() == null
                ? "pour une duree indeterminee"
                : "jusqu'au " + DATE.format(proposition.getDateFin());
        return "CONTRAT DE BAIL A USAGE D'HABITATION\n\n"
                + "Bailleur : " + identite(bien.getBailleur()) + "\n"
                + "Locataire : " + identite(candidature.getLocataire()) + "\n\n"
                + "ARTICLE 1 - OBJET DU CONTRAT\nLe bailleur donne en location au locataire le logement decrit ci-dessous.\n\n"
                + "ARTICLE 2 - DESIGNATION DU LOGEMENT\n" + bien.getTypeBien() + " situe a "
                + bien.getAdresse() + ", usage " + bien.getUsage() + ", logement "
                + bien.getTypeLogement() + ". Inventaire : " + texte(bien.getInventaire()) + ".\n\n"
                + "ARTICLE 3 - DUREE\nLe bail commence le " + DATE.format(proposition.getDateDebut())
                + " et se poursuit " + fin + ".\n\n"
                + "ARTICLE 4 - LOYER ET CHARGES\nLoyer mensuel : " + proposition.getLoyer().toPlainString()
                + " Ar. Charges : " + proposition.getCharges().toPlainString() + " Ar. Mode de paiement : "
                + texte(candidature.getAnnonce().getModePaiement()) + ".\n\n"
                + "ARTICLE 5 - CAUTION ET AVANCE\nCaution : " + proposition.getCaution().toPlainString()
                + " Ar. Avance : " + proposition.getAvance().toPlainString() + " Ar.\n\n"
                + "ARTICLE 6 - OBLIGATIONS DU BAILLEUR\nLe bailleur remet un logement utilisable, garantit une jouissance paisible et effectue les reparations qui lui incombent.\n\n"
                + "ARTICLE 7 - OBLIGATIONS DU LOCATAIRE\nLe locataire paie les sommes convenues, entretient le logement et respecte son usage.\n\n"
                + "ARTICLE 8 - ETAT DES LIEUX ET INVENTAIRE\nUn etat des lieux contradictoire est etabli lors de la remise des cles.\n\n"
                + "ARTICLE 9 - RESILIATION ET PREAVIS\nToute resiliation respecte les formes et delais legaux applicables.\n\n"
                + "ARTICLE 10 - MODIFICATION DU CONTRAT\nSous-location : "
                + texte(proposition.getSousLocation()) + ". Clauses particulieres : "
                + texte(proposition.getClausesSpeciales()) + ".\n\n"
                + "ARTICLE 11 - LITIGES\nLes parties recherchent d'abord une solution amiable avant toute saisine de la juridiction competente.";
    }

    private String identite(Utilisateur utilisateur) {
        return utilisateur.getPrenom() + " " + utilisateur.getNom() + ", piece : "
                + texte(utilisateur.getNumeroPiece()) + ", email : " + utilisateur.getEmail()
                + ", telephone : " + texte(utilisateur.getTelephone());
    }

    private String texte(String valeur) {
        return valeur == null || valeur.isBlank() ? "Non renseigne" : valeur;
    }
}

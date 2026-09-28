package com.legatech.legabail.controller;

import com.legatech.legabail.service.controle.DonneesLocation;
import com.legatech.legabail.service.controle.MoteurReglesService;
import com.legatech.legabail.service.controle.ResultatControle;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControleReglesController {

    private final MoteurReglesService moteurReglesService;

    public ControleReglesController(MoteurReglesService moteurReglesService) {
        this.moteurReglesService = moteurReglesService;
    }

    @GetMapping("/controle-regles")
    public List<ResultatControle> verifier(
            @RequestParam(required = false) String typeLogement,
            @RequestParam(required = false) String usage,
            @RequestParam(required = false) String typeBien,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePermisHabiter,
            @RequestParam(required = false) BigDecimal loyer,
            @RequestParam(required = false) BigDecimal charges,
            @RequestParam(required = false) BigDecimal caution,
            @RequestParam(required = false) BigDecimal avance,
            @RequestParam(required = false) BigDecimal prixLogementNu,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            @RequestParam(required = false) String sousLocation,
            @RequestParam(defaultValue = "false") boolean inventaireFourni,
            @RequestParam(defaultValue = "false") boolean logementLieEmploi) {

        DonneesLocation donnees = new DonneesLocation();
        donnees.setTypeLogement(typeLogement(typeLogement));
        donnees.setUsage(usage(usage));
        donnees.setHotelOuPension(contient(typeBien, "hotel") || contient(typeBien, "pension"));
        donnees.setDatePermisHabiter(datePermisHabiter);
        donnees.setLoyer(loyer);
        donnees.setCharges(charges);
        donnees.setCaution(caution);
        donnees.setAvance(avance);
        donnees.setPrixLogementNu(prixLogementNu);
        donnees.setDateDebut(dateDebut);
        donnees.setDateFin(dateFin);
        donnees.setTypeDuree(dateDebut == null ? null : dateFin == null
                ? DonneesLocation.TypeDuree.INDETERMINEE
                : DonneesLocation.TypeDuree.DETERMINEE);
        donnees.setSousLocation(sousLocation(sousLocation));
        donnees.setInventaireFourni(inventaireFourni);
        donnees.setLogementLieEmploi(logementLieEmploi);

        return moteurReglesService.verifier(donnees);
    }

    private DonneesLocation.TypeLogement typeLogement(String valeur) {
        if (contient(valeur, "meuble")) {
            return DonneesLocation.TypeLogement.MEUBLE;
        }
        if (contient(valeur, "nu")) {
            return DonneesLocation.TypeLogement.NU;
        }
        return null;
    }

    private DonneesLocation.Usage usage(String valeur) {
        if (contient(valeur, "commercial") || contient(valeur, "industriel")) {
            return DonneesLocation.Usage.COMMERCIAL_INDUSTRIEL;
        }
        if (contient(valeur, "professionnel")) {
            return DonneesLocation.Usage.PROFESSIONNEL_NON_COMMERCIAL;
        }
        if (contient(valeur, "habitation")) {
            return DonneesLocation.Usage.HABITATION;
        }
        return null;
    }

    private DonneesLocation.SousLocation sousLocation(String valeur) {
        if (valeur == null || valeur.isBlank()) {
            return null;
        }
        if (contient(valeur, "sans") || contient(valeur, "libre")) {
            return DonneesLocation.SousLocation.DEMANDEE_SANS_AUTORISATION;
        }
        if (contient(valeur, "autor")) {
            return DonneesLocation.SousLocation.AUTORISATION_ECRITE;
        }
        return DonneesLocation.SousLocation.INTERDITE;
    }

    private boolean contient(String valeur, String fragment) {
        if (valeur == null) {
            return false;
        }
        String normalisee = Normalizer.normalize(valeur, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT);
        return normalisee.contains(fragment);
    }
}

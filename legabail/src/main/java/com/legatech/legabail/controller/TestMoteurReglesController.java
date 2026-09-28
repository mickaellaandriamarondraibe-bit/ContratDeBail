package com.legatech.legabail.controller;

import com.legatech.legabail.service.controle.DonneesLocation;
import com.legatech.legabail.service.controle.MoteurReglesService;
import com.legatech.legabail.service.controle.ResultatControle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TestMoteurReglesController {

    private final MoteurReglesService moteurReglesService;

    public TestMoteurReglesController(MoteurReglesService moteurReglesService) {
        this.moteurReglesService = moteurReglesService;
    }

    @GetMapping("/test-moteur-regles")
    public List<ResultatControle> testerMoteur() {
        DonneesLocation donnees = new DonneesLocation();

        donnees.setTypeLogement(DonneesLocation.TypeLogement.NU);
        donnees.setUsage(DonneesLocation.Usage.PROFESSIONNEL_NON_COMMERCIAL);
        donnees.setDatePermisHabiter(LocalDate.of(2020, 1, 1));
        donnees.setLoyer(new BigDecimal("500000"));
        donnees.setCharges(new BigDecimal("50000"));
        donnees.setCaution(new BigDecimal("50000"));
        donnees.setAvance(new BigDecimal("50000"));
        donnees.setTypeDuree(DonneesLocation.TypeDuree.DETERMINEE);
        donnees.setDateDebut(LocalDate.of(2026, 10, 1));
        donnees.setDateFin(LocalDate.of(2027, 10, 1));
        donnees.setSousLocation(DonneesLocation.SousLocation.INTERDITE);

        return moteurReglesService.verifier(donnees);
    }
}
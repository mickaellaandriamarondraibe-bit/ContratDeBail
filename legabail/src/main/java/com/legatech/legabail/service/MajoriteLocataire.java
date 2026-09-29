package com.legatech.legabail.service;

import java.time.LocalDate;

public final class MajoriteLocataire {
    private MajoriteLocataire() {}
    public static void verifier(LocalDate naissance) {
        if (naissance == null) {
            throw new IllegalArgumentException("Renseignez votre date de naissance dans le formulaire de candidature avant de louer.");
        }
        if (naissance.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Vous devez avoir au moins 18 ans pour louer un logement.");
        }
    }
}

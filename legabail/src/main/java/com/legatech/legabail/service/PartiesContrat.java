package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class PartiesContrat {
    private PartiesContrat() { }

    public static Utilisateur verifier(Candidature c, Long utilisateurId) {
        Utilisateur bailleur = c.getAnnonce().getBien().getBailleur();
        if (bailleur.getId().equals(utilisateurId)) { return bailleur; }
        if (c.getLocataire().getId().equals(utilisateurId)) { return c.getLocataire(); }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous ne participez pas a cette candidature.");
    }

    public static void charger(Candidature c) {
        c.getAnnonce().getBien().getBailleur().getNom();
        c.getLocataire().getNom();
    }
}

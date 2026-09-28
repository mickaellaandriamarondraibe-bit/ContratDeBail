package com.legatech.legabail.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

final class SessionPartie {
    private SessionPartie() { }
    static Long locataire(HttpSession session) { return lire(session, "locataireId"); }
    static Long bailleur(HttpSession session) { return lire(session, "bailleurId"); }
    static Long utilisateur(HttpSession session) {
        return session.getAttribute("locataireId") != null ? locataire(session) : bailleur(session);
    }
    private static Long lire(HttpSession session, String cle) {
        if (session.getAttribute(cle) instanceof Long id) { return id; }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Inscrivez-vous pour acceder a cet espace.");
    }
}

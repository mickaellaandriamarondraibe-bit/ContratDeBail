package com.legatech.legabail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(assignableTypes = {LocataireController.class, CandidatureController.class, PropositionController.class, ContratController.class, SignatureController.class})
public class ParcoursLocataireErreurs {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalide(IllegalArgumentException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "locataire/erreur";
    }
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflit(Model model) {
        model.addAttribute("message", "Cet enregistrement existe deja ou a ete modifie. Rechargez la page.");
        return "locataire/erreur";
    }
}

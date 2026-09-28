package com.legatech.legabail.controller;

import com.legatech.legabail.form.LocataireForm;
import com.legatech.legabail.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocataireController {
    private final LocataireService locataires;
    private final CandidatureService candidatures;
    public LocataireController(LocataireService locataires, CandidatureService candidatures) {
        this.locataires = locataires; this.candidatures = candidatures;
    }
    @GetMapping("/inscription/locataire")
    public String formulaire(Model model) {
        model.addAttribute("locataireForm", new LocataireForm());
        return "locataire/inscription-locataire";
    }
    @PostMapping("/inscription/locataire")
    public String inscrire(@Valid @ModelAttribute("locataireForm") LocataireForm form, BindingResult errors, HttpSession session) {
        if (errors.hasErrors()) { return "locataire/inscription-locataire"; }
        try {
            Long id = locataires.enregistrer(form).getId();
            session.removeAttribute("bailleurId");
            session.setAttribute("locataireId", id);
        } catch (IllegalArgumentException e) {
            errors.reject("inscription", e.getMessage());
            return "locataire/inscription-locataire";
        }
        return "redirect:/locataire/candidatures";
    }
    @GetMapping({"/locataire/candidatures", "/espace-locataire"})
    public String espace(Model model, HttpSession session) {
        model.addAttribute("candidatures", candidatures.listerLocataire(SessionPartie.locataire(session)));
        model.addAttribute("titre", "Mes candidatures");
        return "locataire/espace-locataire";
    }
}

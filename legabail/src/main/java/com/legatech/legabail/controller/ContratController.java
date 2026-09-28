package com.legatech.legabail.controller;

import com.legatech.legabail.form.SignatureForm;
import com.legatech.legabail.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContratController {
    private final ContratService contrats;
    private final SignatureService signatures;
    public ContratController(ContratService contrats, SignatureService signatures) { this.contrats = contrats; this.signatures = signatures; }
    @GetMapping("/contrats/{id}")
    public String afficher(@PathVariable Long id, Model model, HttpSession session) {
        charger(id, model, session);
        model.addAttribute("signatureForm", new SignatureForm());
        return "contrat/contrat";
    }
    @GetMapping("/contrats/{id}/imprimer")
    public String imprimer(@PathVariable Long id, Model model, HttpSession session) {
        afficher(id, model, session);
        model.addAttribute("impression", true);
        return "contrat/contrat";
    }
    void charger(Long id, Model model, HttpSession session) {
        Long utilisateurId = SessionPartie.utilisateur(session);
        model.addAttribute("contrat", contrats.consulter(id, utilisateurId));
        var liste = signatures.lister(id, utilisateurId);
        model.addAttribute("signatures", liste);
        model.addAttribute("dejaSigne", liste.stream().anyMatch(s -> s.getUtilisateur().getId().equals(utilisateurId) && "SIGNEE".equals(s.getStatut())));
    }
}

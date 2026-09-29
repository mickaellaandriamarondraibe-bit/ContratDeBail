package com.legatech.legabail.controller;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.*;
import com.legatech.legabail.repository.ContratRepository;
import com.legatech.legabail.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CandidatureController {
    private final CandidatureService candidatures;
    private final PropositionService propositions;
    private final ContratRepository contrats;
    public CandidatureController(CandidatureService candidatures, PropositionService propositions, ContratRepository contrats) {
        this.candidatures = candidatures; this.propositions = propositions; this.contrats = contrats;
    }
    @GetMapping("/annonces/{id}/candidater")
    public String formulaire(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("locataireId") == null) { return "redirect:/inscription/locataire"; }
        model.addAttribute("annonce", candidatures.annoncePubliee(id));
        CandidatureForm form = new CandidatureForm();
        form.setDateNaissance(candidatures.dateNaissance(SessionPartie.locataire(session)));
        model.addAttribute("candidatureForm", form);
        return "locataire/formulaire-locataire";
    }
    @PostMapping("/annonces/{id}/candidater")
    public String envoyer(@PathVariable Long id, @Valid @ModelAttribute("candidatureForm") CandidatureForm form,
                          BindingResult errors, Model model, HttpSession session) {
        Long utilisateurId = SessionPartie.locataire(session);
        if (!errors.hasErrors()) {
            try { return "redirect:/candidatures/" + candidatures.enregistrer(id, utilisateurId, form).getId(); }
            catch (IllegalArgumentException e) { errors.reject("candidature", e.getMessage()); }
        }
        model.addAttribute("annonce", candidatures.annoncePubliee(id));
        return "locataire/formulaire-locataire";
    }
    @GetMapping("/bailleur/candidatures")
    public String reception(Model model, HttpSession session) {
        model.addAttribute("candidatures", candidatures.listerBailleur(SessionPartie.bailleur(session)));
        model.addAttribute("titre", "Candidatures recues");
        return "locataire/espace-locataire";
    }
    @GetMapping("/candidatures/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        chargerDetail(id, SessionPartie.utilisateur(session), model);
        return "locataire/candidature-detail";
    }
    @GetMapping("/candidatures/{id}/negociation")
    public String negociation(@PathVariable Long id, Model model, HttpSession session) {
        Proposition p = chargerDetail(id, SessionPartie.utilisateur(session), model);
        model.addAttribute("propositionForm", PropositionForm.depuis(p));
        return "locataire/negociation";
    }
    Proposition chargerDetail(Long id, Long utilisateurId, Model model) {
        Candidature c = candidatures.consulter(id, utilisateurId);
        List<Proposition> historique = propositions.historique(id, utilisateurId);
        Proposition p = historique.getFirst();
        model.addAttribute("candidature", c); model.addAttribute("proposition", p);
        model.addAttribute("historique", historique);
        model.addAttribute("estLocataire", c.getLocataire().getId().equals(utilisateurId));
        model.addAttribute("contrat", contrats.findByPropositionId(p.getId()).orElse(null));
        return p;
    }
}

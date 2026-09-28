package com.legatech.legabail.controller;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.PropositionForm;
import com.legatech.legabail.repository.PropositionRepository;
import com.legatech.legabail.service.PropositionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PropositionController {
    private final PropositionService propositions;
    private final PropositionRepository repository;
    private final CandidatureController candidatures;
    public PropositionController(PropositionService propositions, PropositionRepository repository, CandidatureController candidatures) {
        this.propositions = propositions; this.repository = repository; this.candidatures = candidatures;
    }
    @PostMapping("/propositions/{id}/accepter-locataire")
    public String accepterLocataire(@PathVariable Long id, HttpSession session) {
        return retour(propositions.accepter(id, SessionPartie.locataire(session), RoleUtilisateur.LOCATAIRE));
    }
    @PostMapping("/propositions/{id}/accepter-bailleur")
    public String accepterBailleur(@PathVariable Long id, HttpSession session) {
        return retour(propositions.accepter(id, SessionPartie.bailleur(session), RoleUtilisateur.BAILLEUR));
    }
    @PostMapping("/propositions/{id}/modifier")
    public String modifier(@PathVariable Long id, @Valid @ModelAttribute("propositionForm") PropositionForm form,
                           BindingResult errors, Model model, HttpSession session) {
        Long utilisateurId = SessionPartie.utilisateur(session);
        Long candidatureId = repository.trouverCandidatureId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        candidatures.chargerDetail(candidatureId, utilisateurId, model);
        if (errors.hasErrors()) { return "locataire/negociation"; }
        try { return retour(propositions.modifier(id, utilisateurId, form)); }
        catch (IllegalArgumentException e) {
            errors.reject("proposition", e.getMessage()); return "locataire/negociation";
        }
    }
    private String retour(Proposition p) { return "redirect:/candidatures/" + p.getCandidature().getId(); }
}

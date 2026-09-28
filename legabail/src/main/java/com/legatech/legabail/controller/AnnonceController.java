package com.legatech.legabail.controller;

import com.legatech.legabail.entity.StatutAnnonce;
import com.legatech.legabail.form.AnnonceForm;
import com.legatech.legabail.service.AnnonceService;
import com.legatech.legabail.service.BienService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnnonceController {

    private final AnnonceService annonceService;
    private final BienService bienService;

    public AnnonceController(AnnonceService annonceService, BienService bienService) {
        this.annonceService = annonceService;
        this.bienService = bienService;
    }

    @GetMapping("/bailleur/annonces/nouvelle")
    public String afficherNouvelleAnnonce(Model model, HttpSession session) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        preparerFormulaire(model, session, new AnnonceForm());
        return "bailleur/formulaire-annonce";
    }

    @PostMapping("/bailleur/annonces")
    public String enregistrerAnnonce(@Valid @ModelAttribute("annonceForm") AnnonceForm form,
                                     BindingResult bindingResult,
                                     HttpSession session,
                                     Model model) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        if (bindingResult.hasErrors()) {
            preparerBiens(model, session);
            return "bailleur/formulaire-annonce";
        }

        annonceService.enregistrerBrouillon(form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    @GetMapping("/annonces")
    public String afficherAnnoncesPubliees(Model model) {
        model.addAttribute("annonces", annonceService.listerAnnoncesPubliees());
        return "annonces";
    }

    @GetMapping("/annonces/{id}")
    public String afficherAnnonce(@PathVariable Long id, Model model) {
        var annonce = annonceService.trouverParId(id);
        if (annonce.getStatut() != StatutAnnonce.PUBLIEE) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        model.addAttribute("annonce", annonce);
        return "annonce-detail";
    }

    @PostMapping("/bailleur/annonces/{id}/publier")
    public String publierAnnonce(@PathVariable Long id, HttpSession session) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        annonceService.publierAnnonce(id, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    @PostMapping("/bailleur/annonces/{id}/archiver")
    public String archiverAnnonce(@PathVariable Long id, HttpSession session) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        annonceService.archiverAnnonce(id, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    private void preparerFormulaire(Model model, HttpSession session, AnnonceForm form) {
        model.addAttribute("annonceForm", form);
        preparerBiens(model, session);
        model.addAttribute("statutsAnnonce", StatutAnnonce.values());
    }

    private void preparerBiens(Model model, HttpSession session) {
        model.addAttribute("biens",
                bienService.listerBiensDuBailleur(BailleurController.getBailleurId(session)));
    }
}

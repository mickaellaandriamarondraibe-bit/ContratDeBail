package com.legatech.legabail.controller;

import com.legatech.legabail.entity.TypeLogement;
import com.legatech.legabail.form.BienForm;
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
public class BienController {

    private final BienService bienService;

    public BienController(BienService bienService) {
        this.bienService = bienService;
    }

    @GetMapping("/bailleur/biens/nouveau")
    public String afficherNouveauBien(Model model) {
        model.addAttribute("bienForm", new BienForm());
        model.addAttribute("typesLogement", TypeLogement.values());
        return "bailleur/formulaire-bien";
    }

    @PostMapping("/bailleur/biens")
    public String enregistrerBien(@Valid @ModelAttribute("bienForm") BienForm form,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typesLogement", TypeLogement.values());
            return "bailleur/formulaire-bien";
        }

        bienService.enregistrerBien(form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    @GetMapping("/bailleur/biens/{id}/modifier")
    public String afficherModification(@PathVariable Long id, Model model, HttpSession session) {
        var bien = bienService.trouverParId(id);
        verifierProprietaire(bien.getBailleur().getId(), session);

        BienForm form = new BienForm();
        form.setAdresse(bien.getAdresse());
        form.setTypeBien(bien.getTypeBien());
        form.setUsage(bien.getUsage());
        form.setTypeLogement(bien.getTypeLogement());
        form.setDatePermisHabiter(bien.getDatePermisHabiter());
        form.setInventaire(bien.getInventaire());
        model.addAttribute("bienForm", form);
        model.addAttribute("bienId", id);
        model.addAttribute("typesLogement", TypeLogement.values());
        return "bailleur/formulaire-bien";
    }

    @PostMapping("/bailleur/biens/{id}")
    public String modifierBien(@PathVariable Long id,
                               @Valid @ModelAttribute("bienForm") BienForm form,
                               BindingResult bindingResult,
                               HttpSession session,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bienId", id);
            model.addAttribute("typesLogement", TypeLogement.values());
            return "bailleur/formulaire-bien";
        }

        bienService.modifierBien(id, form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    private void verifierProprietaire(Long proprietaireId, HttpSession session) {
        if (!proprietaireId.equals(BailleurController.getBailleurId(session))) {
            throw new IllegalArgumentException("Ce bien n'appartient pas à ce bailleur.");
        }
    }
}

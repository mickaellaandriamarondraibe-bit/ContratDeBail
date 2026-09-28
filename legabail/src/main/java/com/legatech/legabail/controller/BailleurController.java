package com.legatech.legabail.controller;

import com.legatech.legabail.form.BailleurForm;
import com.legatech.legabail.service.AnnonceService;
import com.legatech.legabail.service.BienService;
import com.legatech.legabail.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BailleurController {

    private static final String BAILLEUR_ID = "bailleurId";

    private final UtilisateurService utilisateurService;
    private final BienService bienService;
    private final AnnonceService annonceService;

    public BailleurController(UtilisateurService utilisateurService,
                              BienService bienService,
                              AnnonceService annonceService) {
        this.utilisateurService = utilisateurService;
        this.bienService = bienService;
        this.annonceService = annonceService;
    }

    @GetMapping("/inscription/bailleur")
    public String afficherInscription(Model model) {
        model.addAttribute("bailleurForm", new BailleurForm());
        return "bailleur/inscription-bailleur";
    }

    @PostMapping("/inscription/bailleur")
    public String inscrireBailleur(@Valid @ModelAttribute("bailleurForm") BailleurForm form,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "bailleur/inscription-bailleur";
        }

        try {
            session.setAttribute(BAILLEUR_ID, utilisateurService.enregistrerBailleur(form).getId());
            session.removeAttribute("locataireId");
        } catch (IllegalArgumentException exception) {
            bindingResult.reject("inscription.invalide", exception.getMessage());
            return "bailleur/inscription-bailleur";
        }
        return "redirect:/espace-bailleur";
    }

    @GetMapping("/espace-bailleur")
    public String afficherEspaceBailleur(Model model, HttpSession session) {
        Long bailleurId = getBailleurId(session);
        model.addAttribute("bailleur", utilisateurService.trouverParId(bailleurId));
        model.addAttribute("biens", bienService.listerBiensDuBailleur(bailleurId));
        model.addAttribute("annonces", annonceService.listerAnnoncesDuBailleur(bailleurId));
        return "bailleur/espace-bailleur";
    }

    static Long getBailleurId(HttpSession session) {
        Object value = session.getAttribute(BAILLEUR_ID);
        if (!(value instanceof Long bailleurId)) {
            throw new IllegalStateException("Aucun bailleur n'est connecté.");
        }
        return bailleurId;
    }
}

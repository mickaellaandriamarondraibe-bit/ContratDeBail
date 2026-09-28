package com.legatech.legabail.controller;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.form.ConnexionForm;
import com.legatech.legabail.service.AuthentificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnexionController {

    private final AuthentificationService authentificationService;

    public ConnexionController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @GetMapping("/connexion")
    public String afficherConnexion(Model model) {
        if (!model.containsAttribute("connexionForm")) {
            model.addAttribute("connexionForm", new ConnexionForm());
        }
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connecter(
            @Valid @ModelAttribute("connexionForm") ConnexionForm form,
            BindingResult erreurs,
            HttpServletRequest request) {
        if (erreurs.hasErrors()) {
            return "connexion";
        }

        try {
            Utilisateur utilisateur = authentificationService.authentifier(
                    form.getEmail(), form.getMotDePasse());
            HttpSession ancienneSession = request.getSession(false);
            if (ancienneSession != null) {
                ancienneSession.invalidate();
            }
            HttpSession session = request.getSession(true);
            if (utilisateur.getRole() == RoleUtilisateur.BAILLEUR) {
                session.setAttribute("bailleurId", utilisateur.getId());
                return "redirect:/espace-bailleur";
            }
            session.setAttribute("locataireId", utilisateur.getId());
            return "redirect:/locataire/candidatures";
        } catch (IllegalArgumentException exception) {
            erreurs.reject("connexion.invalide", exception.getMessage());
            return "connexion";
        }
    }

    @PostMapping("/deconnexion")
    public String deconnecter(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}

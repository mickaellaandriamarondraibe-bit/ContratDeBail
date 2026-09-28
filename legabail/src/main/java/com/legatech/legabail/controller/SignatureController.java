package com.legatech.legabail.controller;

import com.legatech.legabail.form.SignatureForm;
import com.legatech.legabail.service.SignatureService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignatureController {
    private final SignatureService signatures;
    private final ContratController contrats;
    public SignatureController(SignatureService signatures, ContratController contrats) { this.signatures = signatures; this.contrats = contrats; }
    @PostMapping("/contrats/{id}/signer")
    public String signer(@PathVariable Long id, @Valid @ModelAttribute("signatureForm") SignatureForm form,
                         BindingResult errors, Model model, HttpSession session) {
        if (errors.hasErrors()) { contrats.charger(id, model, session); return "contrat/contrat"; }
        signatures.signer(id, SessionPartie.utilisateur(session), form);
        return "redirect:/contrats/" + id;
    }
}

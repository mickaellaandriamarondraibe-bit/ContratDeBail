package com.legatech.legabail.controller;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.form.SignatureForm;
import com.legatech.legabail.service.ContratService;
import com.legatech.legabail.service.SignatureService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContratController {

    private final ContratService contrats;
    private final SignatureService signatures;

    public ContratController(ContratService contrats, SignatureService signatures) {
        this.contrats = contrats;
        this.signatures = signatures;
    }

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
        var contrat = contrats.consulter(id, utilisateurId);
        var proposition = contrat.getProposition();
        var candidature = proposition.getCandidature();
        var annonce = candidature.getAnnonce();
        var bien = annonce.getBien();
        var liste = signatures.lister(id, utilisateurId);
        BigDecimal totalMensuel = proposition.getLoyer().add(proposition.getCharges());
        BigDecimal garantieTotale = proposition.getCaution().add(proposition.getAvance());

        model.addAttribute("contrat", contrat);
        model.addAttribute("proposition", proposition);
        model.addAttribute("candidature", candidature);
        model.addAttribute("annonce", annonce);
        model.addAttribute("bien", bien);
        model.addAttribute("bailleur", bien.getBailleur());
        model.addAttribute("locataire", candidature.getLocataire());
        model.addAttribute("signatures", liste);
        model.addAttribute("bailleurSignature", liste.stream()
                .filter(s -> s.getUtilisateur().getRole() == RoleUtilisateur.BAILLEUR)
                .findFirst().orElse(null));
        model.addAttribute("locataireSignature", liste.stream()
                .filter(s -> s.getUtilisateur().getRole() == RoleUtilisateur.LOCATAIRE)
                .findFirst().orElse(null));
        model.addAttribute("loyerTexte", montant(proposition.getLoyer()));
        model.addAttribute("chargesTexte", montant(proposition.getCharges()));
        model.addAttribute("totalMensuelTexte", montant(totalMensuel));
        model.addAttribute("cautionTexte", montant(proposition.getCaution()));
        model.addAttribute("avanceTexte", montant(proposition.getAvance()));
        model.addAttribute("garantieTotaleTexte", montant(garantieTotale));
        model.addAttribute("dateDebutTexte", dateFrancaise(proposition.getDateDebut()));
        model.addAttribute("dateFinTexte", dateFrancaise(proposition.getDateFin()));
        model.addAttribute("dateGenerationTexte", dateFrancaise(contrat.getDateGeneration().toLocalDate()));
        model.addAttribute("dureeMois", proposition.getDateFin() == null ? null
                : ChronoUnit.MONTHS.between(proposition.getDateDebut(), proposition.getDateFin().plusDays(1)));
        model.addAttribute("dejaSigne", liste.stream().anyMatch(s ->
                s.getUtilisateur().getId().equals(utilisateurId) && "SIGNEE".equals(s.getStatut())));
    }

    private String montant(BigDecimal valeur) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.FRANCE);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(0);
        return format.format(valeur);
    }

    private String dateFrancaise(LocalDate date) {
        if (date == null) {
            return null;
        }
        String jour = date.getDayOfMonth() == 1 ? "1er" : String.valueOf(date.getDayOfMonth());
        return jour + " " + date.format(DateTimeFormatter.ofPattern("MMMM uuuu", Locale.FRANCE));
    }
}

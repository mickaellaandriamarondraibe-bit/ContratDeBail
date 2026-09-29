package com.legatech.legabail.controller;

import com.legatech.legabail.entity.Contrat;
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
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContratController {

    private static final int CONTRATS_PAR_PAGE = 10;

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

    @GetMapping("/bailleur/contrats")
    public String listerBailleur(
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) LocalDate dateDebut,
            @RequestParam(required = false) LocalDate dateFin,
            @RequestParam(required = false) String statut,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) LocalDate dateNaissance,
            @RequestParam(defaultValue = "0") int page,
            Model model,
            HttpSession session) {

        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }

        List<Contrat> contratsBailleur = contrats.listerBailleur(
                BailleurController.getBailleurId(session)
        );

        String recherche = ville == null ? "" : ville.strip();

        List<Contrat> contratsFiltres = contratsBailleur.stream()
                .filter(contrat -> age == null
                        || age.equals(contrat.getProposition()
                        .getCandidature()
                        .getLocataire()
                        .getAge()))
                .filter(contrat -> dateNaissance == null
                        || dateNaissance.equals(contrat.getProposition()
                        .getCandidature()
                        .getLocataire()
                        .getDateNaissance()))
                .filter(contrat -> recherche.isBlank()
                        || contrat.getProposition()
                        .getCandidature()
                        .getAnnonce()
                        .getBien()
                        .getAdresse()
                        .toLowerCase(Locale.ROOT)
                        .contains(recherche.toLowerCase(Locale.ROOT)))
                .filter(contrat -> dateDebut == null
                        || !contrat.getDateGeneration().toLocalDate().isBefore(dateDebut))
                .filter(contrat -> dateFin == null
                        || !contrat.getDateGeneration().toLocalDate().isAfter(dateFin))
                .filter(contrat -> statut == null
                        || statut.isBlank()
                        || statut.equals(contrat.getStatut()))
                .toList();

        int totalPages = contratsFiltres.isEmpty()
                ? 0
                : (contratsFiltres.size() + CONTRATS_PAR_PAGE - 1) / CONTRATS_PAR_PAGE;

        int pageCourante = totalPages == 0
                ? 0
                : Math.min(Math.max(page, 0), totalPages - 1);

        int premierIndex = pageCourante * CONTRATS_PAR_PAGE;
        int dernierIndex = Math.min(premierIndex + CONTRATS_PAR_PAGE, contratsFiltres.size());

        List<Contrat> contratsPage = contratsFiltres.subList(premierIndex, dernierIndex);

        boolean filtresActifs = !recherche.isBlank()
                || dateDebut != null
                || dateFin != null
                || age != null
                || dateNaissance != null
                || (statut != null && !statut.isBlank());

        model.addAttribute("contrats", contratsPage);
        model.addAttribute("totalContrats", contratsBailleur.size());
        model.addAttribute("resultats", contratsFiltres.size());

        model.addAttribute("ville", recherche);
        model.addAttribute("dateDebut", dateDebut);
        model.addAttribute("dateFin", dateFin);
        model.addAttribute("statut", statut);
        model.addAttribute("age", age);
        model.addAttribute("dateNaissance", dateNaissance);

        model.addAttribute("filtresActifs", filtresActifs);
        model.addAttribute("pageCourante", pageCourante);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("debutResultat", contratsPage.isEmpty() ? 0 : premierIndex + 1);
        model.addAttribute("finResultat", dernierIndex);

        return "bailleur/contrats";
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
                .filter(signature -> signature.getUtilisateur().getRole() == RoleUtilisateur.BAILLEUR)
                .findFirst()
                .orElse(null));

        model.addAttribute("locataireSignature", liste.stream()
                .filter(signature -> signature.getUtilisateur().getRole() == RoleUtilisateur.LOCATAIRE)
                .findFirst()
                .orElse(null));

        model.addAttribute("loyerTexte", montant(proposition.getLoyer()));
        model.addAttribute("chargesTexte", montant(proposition.getCharges()));
        model.addAttribute("totalMensuelTexte", montant(totalMensuel));
        model.addAttribute("cautionTexte", montant(proposition.getCaution()));
        model.addAttribute("avanceTexte", montant(proposition.getAvance()));
        model.addAttribute("garantieTotaleTexte", montant(garantieTotale));

        model.addAttribute("dateDebutTexte", dateFrancaise(proposition.getDateDebut()));
        model.addAttribute("dateFinTexte", dateFrancaise(proposition.getDateFin()));
        model.addAttribute("dateGenerationTexte", dateFrancaise(contrat.getDateGeneration().toLocalDate()));

        model.addAttribute("dureeMois", proposition.getDateFin() == null
                ? null
                : ChronoUnit.MONTHS.between(
                        proposition.getDateDebut(),
                        proposition.getDateFin().plusDays(1)
                ));

        model.addAttribute("dejaSigne", liste.stream().anyMatch(signature ->
                signature.getUtilisateur().getId().equals(utilisateurId)
                        && "SIGNEE".equals(signature.getStatut())));
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

        String jour = date.getDayOfMonth() == 1
                ? "1er"
                : String.valueOf(date.getDayOfMonth());

        return jour + " " + date.format(
                DateTimeFormatter.ofPattern("MMMM uuuu", Locale.FRANCE)
        );
    }
}
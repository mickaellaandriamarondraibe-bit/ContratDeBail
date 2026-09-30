package com.legatech.legabail.controller;

import com.legatech.legabail.entity.TypeLogement;
import com.legatech.legabail.form.BienForm;
import com.legatech.legabail.service.BienService;
import com.legatech.legabail.service.StockageImageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BienController {

    private final BienService bienService;
    private final StockageImageService stockageImageService;

    public BienController(BienService bienService, StockageImageService stockageImageService) {
        this.bienService = bienService;
        this.stockageImageService = stockageImageService;
    }

    @GetMapping("/bailleur/biens/nouveau")
    public String afficherNouveauBien(Model model, HttpSession session) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        model.addAttribute("bienForm", new BienForm());
        model.addAttribute("typesLogement", TypeLogement.values());
        return "bailleur/formulaire-bien";
    }

    @PostMapping("/bailleur/biens")
    public String enregistrerBien(@Valid @ModelAttribute("bienForm") BienForm form,
                                  BindingResult bindingResult,
                                  @RequestParam(name = "image", required = false) MultipartFile image,
                                  HttpSession session,
                                  Model model) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        enregistrerImage(image, form, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("typesLogement", TypeLogement.values());
            return "bailleur/formulaire-bien";
        }

        bienService.enregistrerBien(form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    @GetMapping("/bailleur/biens/{id}/modifier")
    public String afficherModification(@PathVariable Long id, Model model, HttpSession session) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        var bien = bienService.trouverParId(id);
        verifierProprietaire(bien.getBailleur().getId(), session);

        BienForm form = new BienForm();
        form.setAdresse(bien.getAdresse());
        form.setTypeBien(bien.getTypeBien());
        form.setUsage(bien.getUsage());
        form.setTypeLogement(bien.getTypeLogement());
        form.setDatePermisHabiter(bien.getDatePermisHabiter());
        form.setInventaire(bien.getInventaire());
        form.setImageUrl(bien.getImageUrl());
        form.setNombrePieces(bien.getNombrePieces());
        form.setNombreChambres(bien.getNombreChambres());
        form.setNombreDouches(bien.getNombreDouches());
        form.setNombreSallesBains(bien.getNombreSallesBains());
        form.setNombreWc(bien.getNombreWc());
        form.setNombreCuisines(bien.getNombreCuisines());
        form.setNombreEtages(bien.getNombreEtages());
        form.setEtage(bien.getEtage());
        form.setPlacesParking(bien.getPlacesParking());
        form.setSurfaceHabitable(bien.getSurfaceHabitable());
        form.setSurfaceTerrain(bien.getSurfaceTerrain());
        form.setCloture(bien.getCloture());
        form.setGarage(bien.getGarage());
        form.setJardin(bien.getJardin());
        form.setBalcon(bien.getBalcon());
        form.setTerrasse(bien.getTerrasse());
        form.setEauCourante(bien.getEauCourante());
        form.setElectricite(bien.getElectricite());
        form.setDescription(bien.getDescription());
        model.addAttribute("bienForm", form);
        model.addAttribute("bienId", id);
        model.addAttribute("typesLogement", TypeLogement.values());
        return "bailleur/formulaire-bien";
    }

    @PostMapping("/bailleur/biens/{id}")
    public String modifierBien(@PathVariable Long id,
                               @Valid @ModelAttribute("bienForm") BienForm form,
                               BindingResult bindingResult,
                               @RequestParam(name = "image", required = false) MultipartFile image,
                               HttpSession session,
                               Model model) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        enregistrerImage(image, form, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("bienId", id);
            model.addAttribute("typesLogement", TypeLogement.values());
            return "bailleur/formulaire-bien";
        }

        bienService.modifierBien(id, form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    private void enregistrerImage(MultipartFile image, BienForm form, BindingResult bindingResult) {
        try {
            String imageUrl = stockageImageService.stocker(image);
            if (imageUrl != null) {
                form.setImageUrl(imageUrl);
            }
        } catch (IllegalArgumentException | IllegalStateException exception) {
            bindingResult.rejectValue("imageUrl", "image.invalide", exception.getMessage());
        }
    }

    private void verifierProprietaire(Long proprietaireId, HttpSession session) {
        if (!proprietaireId.equals(BailleurController.getBailleurId(session))) {
            throw new IllegalArgumentException("Ce bien n'appartient pas à ce bailleur.");
        }
    }
}

package com.legatech.legabail.controller;

import com.legatech.legabail.entity.TypeLogement;
import com.legatech.legabail.form.BienForm;
import com.legatech.legabail.service.BienService;
import com.legatech.legabail.service.StockageImageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder("bienForm")
    public void protegerPhotos(WebDataBinder binder) {
        binder.setDisallowedFields("photosAjoutees", "photosAjoutees[*]");
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
                                  @RequestParam(name = "image", required = false) List<MultipartFile> image,
                                  HttpSession session,
                                  Model model) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        form.setImageUrl(null);
        enregistrerImages(image, form, bindingResult, List.of());
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
        model.addAttribute("photosExistantes", bien.getGaleriePhotos());
        model.addAttribute("bienForm", form);
        model.addAttribute("bienId", id);
        model.addAttribute("typesLogement", TypeLogement.values());
        return "bailleur/formulaire-bien";
    }

    @PostMapping("/bailleur/biens/{id}")
    public String modifierBien(@PathVariable Long id,
                               @Valid @ModelAttribute("bienForm") BienForm form,
                               BindingResult bindingResult,
                               @RequestParam(name = "image", required = false) List<MultipartFile> image,
                               HttpSession session,
                               Model model) {
        if (!BailleurController.estConnecte(session)) {
            return "redirect:/connexion";
        }
        var bien = bienService.trouverParId(id);
        verifierProprietaire(bien.getBailleur().getId(), session);
        form.setImageUrl(bien.getImageUrl());
        model.addAttribute("photosExistantes", bien.getGaleriePhotos());
        enregistrerImages(image, form, bindingResult, bien.getGaleriePhotos());
        if (bindingResult.hasErrors()) {
            model.addAttribute("bienId", id);
            model.addAttribute("typesLogement", TypeLogement.values());
            return "bailleur/formulaire-bien";
        }

        bienService.modifierBien(id, form, BailleurController.getBailleurId(session));
        return "redirect:/espace-bailleur";
    }

    private void enregistrerImages(List<MultipartFile> images, BienForm form,
                                   BindingResult bindingResult, List<String> existantes) {
        if (bindingResult.hasErrors()) return;
        List<MultipartFile> fichiers = images == null ? List.of()
                : images.stream().filter(image -> !image.isEmpty()).toList();
        long conservees = existantes.stream().filter(url -> !form.getPhotosSupprimees().contains(url)).count();
        if (conservees + fichiers.size() > 10) {
            bindingResult.rejectValue("imageUrl", "image.limite", "Vous pouvez conserver au maximum 10 photos par bien.");
            return;
        }
        try {
            for (MultipartFile fichier : fichiers) stockageImageService.valider(fichier);
            for (MultipartFile fichier : fichiers) form.getPhotosAjoutees().add(stockageImageService.stocker(fichier));
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

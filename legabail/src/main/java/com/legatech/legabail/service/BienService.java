package com.legatech.legabail.service;

import com.legatech.legabail.entity.Bien;
import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.form.BienForm;
import com.legatech.legabail.repository.BienRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BienService {

    private final BienRepository bienRepository;
    private final UtilisateurService utilisateurService;

    public BienService(BienRepository bienRepository, UtilisateurService utilisateurService) {
        this.bienRepository = bienRepository;
        this.utilisateurService = utilisateurService;
    }

    public Bien enregistrerBien(BienForm form, Long bailleurId) {
        Utilisateur bailleur = utilisateurService.trouverParId(bailleurId);
        verifierBailleur(bailleur);

        Bien bien = new Bien();
        mettreAJourBien(bien, form);
        bien.setBailleur(bailleur);
        return bienRepository.save(bien);
    }

    public Bien modifierBien(Long bienId, BienForm form, Long bailleurId) {
        Bien bien = trouverParId(bienId);
        verifierProprietaire(bien, bailleurId);
        mettreAJourBien(bien, form);
        return bienRepository.save(bien);
    }

    @Transactional(readOnly = true)
    public Bien trouverParId(Long id) {
        return bienRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bien introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public List<Bien> listerBiensDuBailleur(Long bailleurId) {
        return bienRepository.findByBailleurId(bailleurId);
    }

    private void mettreAJourBien(Bien bien, BienForm form) {
        bien.setAdresse(form.getAdresse());
        bien.setTypeBien(form.getTypeBien());
        bien.setUsage(form.getUsage());
        bien.setTypeLogement(form.getTypeLogement());
        bien.setDatePermisHabiter(form.getDatePermisHabiter());
        bien.setInventaire(form.getInventaire());
        var galerie = bien.getGaleriePhotos();
        galerie.removeAll(form.getPhotosSupprimees());
        galerie.addAll(form.getPhotosAjoutees());
        bien.setImageUrl(galerie.isEmpty() ? null : galerie.removeFirst());
        bien.getPhotos().clear();
        bien.getPhotos().addAll(galerie);
        bien.setNombrePieces(form.getNombrePieces());
        bien.setNombreChambres(form.getNombreChambres());
        bien.setNombreDouches(form.getNombreDouches());
        bien.setNombreSallesBains(form.getNombreSallesBains());
        bien.setNombreWc(form.getNombreWc());
        bien.setNombreCuisines(form.getNombreCuisines());
        bien.setNombreEtages(form.getNombreEtages());
        bien.setEtage(form.getEtage());
        bien.setPlacesParking(form.getPlacesParking());
        bien.setSurfaceHabitable(form.getSurfaceHabitable());
        bien.setSurfaceTerrain(form.getSurfaceTerrain());
        bien.setCloture(form.getCloture());
        bien.setGarage(form.getGarage());
        bien.setJardin(form.getJardin());
        bien.setBalcon(form.getBalcon());
        bien.setTerrasse(form.getTerrasse());
        bien.setEauCourante(form.getEauCourante());
        bien.setElectricite(form.getElectricite());
        bien.setDescription(form.getDescription());
    }

    private void verifierProprietaire(Bien bien, Long bailleurId) {
        if (!bien.getBailleur().getId().equals(bailleurId)) {
            throw new IllegalArgumentException("Ce bien n'appartient pas à ce bailleur.");
        }
    }

    private void verifierBailleur(Utilisateur utilisateur) {
        if (utilisateur.getRole() != RoleUtilisateur.BAILLEUR) {
            throw new IllegalArgumentException("L'utilisateur n'est pas un bailleur.");
        }
    }
}


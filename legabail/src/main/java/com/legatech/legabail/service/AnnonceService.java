package com.legatech.legabail.service;

import com.legatech.legabail.entity.Annonce;
import com.legatech.legabail.entity.Bien;
import com.legatech.legabail.entity.StatutAnnonce;
import com.legatech.legabail.form.AnnonceForm;
import com.legatech.legabail.repository.AnnonceRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final BienService bienService;

    public AnnonceService(AnnonceRepository annonceRepository, BienService bienService) {
        this.annonceRepository = annonceRepository;
        this.bienService = bienService;
    }

    public Annonce enregistrerBrouillon(AnnonceForm form, Long bailleurId) {
        Bien bien = bienService.trouverParId(form.getBienId());
        verifierProprietaire(bien, bailleurId);

        Annonce annonce = new Annonce();
        annonce.setBien(bien);
        annonce.setDateCreation(OffsetDateTime.now());
        mettreAJourAnnonce(annonce, form);
        annonce.setStatut(StatutAnnonce.BROUILLON);
        return annonceRepository.save(annonce);
    }

    public Annonce modifierAnnonce(Long annonceId, AnnonceForm form, Long bailleurId) {
        Annonce annonce = trouverParId(annonceId);
        verifierProprietaire(annonce.getBien(), bailleurId);
        if (annonce.getStatut() == StatutAnnonce.ARCHIVEE
                || annonce.getStatut() == StatutAnnonce.LOUEE) {
            throw new IllegalStateException("Cette annonce ne peut plus être modifiée.");
        }

        Bien bien = bienService.trouverParId(form.getBienId());
        verifierProprietaire(bien, bailleurId);
        annonce.setBien(bien);
        mettreAJourAnnonce(annonce, form);
        return annonceRepository.save(annonce);
    }

    public Annonce publierAnnonce(Long annonceId, Long bailleurId) {
        Annonce annonce = trouverParId(annonceId);
        verifierProprietaire(annonce.getBien(), bailleurId);
        if (annonce.getStatut() != StatutAnnonce.BROUILLON) {
            throw new IllegalStateException("Seule une annonce brouillon peut être publiée.");
        }
        annonce.setStatut(StatutAnnonce.PUBLIEE);
        return annonceRepository.save(annonce);
    }

    public Annonce archiverAnnonce(Long annonceId, Long bailleurId) {
        Annonce annonce = trouverParId(annonceId);
        verifierProprietaire(annonce.getBien(), bailleurId);
        if (annonce.getStatut() == StatutAnnonce.LOUEE) {
            throw new IllegalStateException("Une annonce louée ne peut pas être archivée.");
        }
        annonce.setStatut(StatutAnnonce.ARCHIVEE);
        return annonceRepository.save(annonce);
    }

    @Transactional(readOnly = true)
    public Annonce trouverParId(Long id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public List<Annonce> listerAnnoncesDuBailleur(Long bailleurId) {
        return annonceRepository.findByBienBailleurId(bailleurId);
    }

    @Transactional(readOnly = true)
    public List<Annonce> listerAnnoncesPubliees() {
        return annonceRepository.findByStatut(StatutAnnonce.PUBLIEE);
    }

    private void mettreAJourAnnonce(Annonce annonce, AnnonceForm form) {
        annonce.setLoyer(form.getLoyer());
        annonce.setCharges(form.getCharges());
        annonce.setCaution(form.getCaution());
        annonce.setAvance(form.getAvance());
        annonce.setPrixLogementNu(form.getPrixLogementNu());
        annonce.setDateDebut(form.getDateDebut());
        annonce.setDateFin(form.getDateFin());
        annonce.setSousLocation(form.getSousLocation());
        annonce.setModePaiement(form.getModePaiement());
        annonce.setClausesSpeciales(form.getClausesSpeciales());
    }

    private void verifierProprietaire(Bien bien, Long bailleurId) {
        if (!bien.getBailleur().getId().equals(bailleurId)) {
            throw new IllegalArgumentException("Ce bien n'appartient pas à ce bailleur.");
        }
    }
}

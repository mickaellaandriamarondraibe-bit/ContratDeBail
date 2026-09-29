package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.CandidatureForm;
import com.legatech.legabail.repository.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Service @Validated
@Transactional
public class CandidatureService {
    private final CandidatureRepository candidatures;
    private final AnnonceRepository annonces;
    private final UtilisateurRepository utilisateurs;
    private final PropositionRepository propositions;
    public CandidatureService(CandidatureRepository candidatures, AnnonceRepository annonces,
                              UtilisateurRepository utilisateurs, PropositionRepository propositions) {
        this.candidatures = candidatures; this.annonces = annonces;
        this.utilisateurs = utilisateurs; this.propositions = propositions;
    }

    @Transactional(readOnly = true)
    public Annonce annoncePubliee(Long id) {
        Annonce a = annonces.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (a.getStatut() != StatutAnnonce.PUBLIEE) { throw new IllegalArgumentException("Cette annonce n'est plus disponible."); }
        a.getBien().getBailleur().getNom();
        return a;
    }

    @Transactional(readOnly = true)
    public java.time.LocalDate dateNaissance(Long id) {
        return utilisateurs.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)).getDateNaissance();
    }

    public Candidature enregistrer(Long annonceId, Long locataireId, @Valid CandidatureForm form) {
        Annonce a = annoncePubliee(annonceId);
        Utilisateur u = utilisateurs.findById(locataireId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if (u.getRole() != RoleUtilisateur.LOCATAIRE) { throw new ResponseStatusException(HttpStatus.FORBIDDEN); }
        if (u.getDateNaissance() == null) {
            MajoriteLocataire.verifier(form.getDateNaissance());
            u.setDateNaissance(form.getDateNaissance());
        }
        if (form.getDateNaissance() != null && !form.getDateNaissance().equals(u.getDateNaissance())) {
            throw new IllegalArgumentException("La date de naissance doit correspondre à celle de votre compte.");
        }
        MajoriteLocataire.verifier(u.getDateNaissance());
        if (candidatures.existsByAnnonceIdAndLocataireId(annonceId, locataireId)) {
            throw new IllegalArgumentException("Vous avez deja candidate pour cette annonce.");
        }
        Candidature c = new Candidature();
        c.setAnnonce(a); c.setLocataire(u); c.setNombreOccupants(form.getNombreOccupants());
        c.setUsagePrevu(form.getUsagePrevu()); c.setMessage(form.getMessage());
        candidatures.save(c);
        Proposition p = new Proposition();
        p.setCandidature(c); p.setNumeroVersion(1); p.setLoyer(a.getLoyer()); p.setCharges(a.getCharges());
        p.setCaution(a.getCaution()); p.setAvance(a.getAvance()); p.setDateDebut(a.getDateDebut());
        p.setDateFin(a.getDateFin()); p.setSousLocation(a.getSousLocation()); p.setClausesSpeciales(a.getClausesSpeciales());
        propositions.save(p);
        return c;
    }

    @Transactional(readOnly = true)
    public Candidature consulter(Long id, Long utilisateurId) {
        Candidature c = candidatures.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        PartiesContrat.verifier(c, utilisateurId); PartiesContrat.charger(c);
        return c;
    }

    @Transactional(readOnly = true)
    public List<Candidature> listerLocataire(Long id) { return candidatures.findByLocataireIdOrderByDateCandidatureDesc(id); }
    @Transactional(readOnly = true)
    public List<Candidature> listerBailleur(Long id) { return candidatures.findByAnnonceBienBailleurIdOrderByDateCandidatureDesc(id); }
}

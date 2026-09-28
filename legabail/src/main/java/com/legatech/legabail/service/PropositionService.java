package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.PropositionForm;
import com.legatech.legabail.repository.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Service @Validated @Transactional
public class PropositionService {
    private final PropositionRepository propositions;
    private final CandidatureRepository candidatures;
    private final ContratService contrats;
    public PropositionService(PropositionRepository propositions, CandidatureRepository candidatures, ContratService contrats) {
        this.propositions = propositions; this.candidatures = candidatures; this.contrats = contrats;
    }

    private Proposition verrouillerDerniere(Long id, Long utilisateurId) {
        // Lock the candidature before loading the proposal so concurrent decisions share one version.
        Long candidatureId = propositions.trouverCandidatureId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Candidature c = candidatures.verrouiller(candidatureId).orElseThrow();
        PartiesContrat.verifier(c, utilisateurId);
        Proposition p = propositions.findFirstByCandidatureIdOrderByNumeroVersionDesc(c.getId()).orElseThrow();
        if (!p.getId().equals(id)) { throw new IllegalArgumentException("Cette version est obsolete. Consultez la derniere proposition."); }
        if ("ACCEPTEE".equals(c.getStatut()) || "REFUSEE".equals(c.getStatut())) {
            throw new IllegalArgumentException("Cette candidature est deja finalisee.");
        }
        return p;
    }

    public Proposition accepter(Long id, Long utilisateurId, RoleUtilisateur role) {
        Proposition p = verrouillerDerniere(id, utilisateurId);
        Utilisateur u = PartiesContrat.verifier(p.getCandidature(), utilisateurId);
        if (u.getRole() != role) { throw new ResponseStatusException(HttpStatus.FORBIDDEN); }
        if (role == RoleUtilisateur.BAILLEUR) { p.setBailleurAccepte(true); }
        else { p.setLocataireAccepte(true); }
        if (p.isBailleurAccepte() && p.isLocataireAccepte()) { contrats.generer(p); }
        return p;
    }

    public Proposition modifier(Long id, Long utilisateurId, @Valid PropositionForm form) {
        Proposition precedente = verrouillerDerniere(id, utilisateurId);
        Proposition nouvelle = new Proposition();
        nouvelle.setCandidature(precedente.getCandidature());
        nouvelle.setNumeroVersion(precedente.getNumeroVersion() + 1);
        form.appliquer(nouvelle);
        precedente.getCandidature().setStatut("NEGOCIATION");
        return propositions.save(nouvelle);
    }

    @Transactional(readOnly = true)
    public List<Proposition> historique(Long candidatureId, Long utilisateurId) {
        Candidature c = candidatures.findById(candidatureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        PartiesContrat.verifier(c, utilisateurId);
        return propositions.findByCandidatureIdOrderByNumeroVersionDesc(candidatureId);
    }
}

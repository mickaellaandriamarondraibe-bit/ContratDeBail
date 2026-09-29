package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.SignatureForm;
import com.legatech.legabail.repository.*;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Service @Validated @Transactional
public class SignatureService {
    private final ContratRepository contrats;
    private final SignatureRepository signatures;
    public SignatureService(ContratRepository contrats, SignatureRepository signatures) {
        this.contrats = contrats; this.signatures = signatures;
    }
    public void signer(Long id, Long utilisateurId, @Valid SignatureForm form) {
        if (!form.isConfirmation()) { throw new IllegalArgumentException("Confirmez votre signature."); }
        Contrat contrat = contrats.verrouiller(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Candidature c = contrat.getProposition().getCandidature();
        Utilisateur u = PartiesContrat.verifier(c, utilisateurId);
        if (signatures.existsByContratIdAndUtilisateurIdAndStatut(id, utilisateurId, "SIGNEE")) { return; }
        if (!"A_SIGNER".equals(contrat.getStatut())) { throw new IllegalArgumentException("Ce contrat ne peut plus etre signe."); }
        MajoriteLocataire.verifier(c.getLocataire().getDateNaissance());
        Signature s = new Signature();
        s.setContrat(contrat); s.setUtilisateur(u); s.setDateSignature(OffsetDateTime.now());
        signatures.saveAndFlush(s);
        if (signatures.existsByContratIdAndUtilisateurIdAndStatut(id, c.getLocataire().getId(), "SIGNEE")
                && signatures.existsByContratIdAndUtilisateurIdAndStatut(id, c.getAnnonce().getBien().getBailleur().getId(), "SIGNEE")) {
            contrat.setStatut("SIGNE");
        }
    }
    @Transactional(readOnly = true)
    public List<Signature> lister(Long id, Long utilisateurId) {
        Contrat contrat = contrats.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        PartiesContrat.verifier(contrat.getProposition().getCandidature(), utilisateurId);
        return signatures.findByContratIdOrderByDateSignatureAsc(id);
    }
}

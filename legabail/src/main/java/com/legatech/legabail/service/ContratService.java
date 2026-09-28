package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.repository.*;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service @Transactional
public class ContratService {
    private final ContratRepository contrats;
    private final CandidatureRepository candidatures;
    private final PropositionRepository propositions;
    public ContratService(ContratRepository contrats, CandidatureRepository candidatures, PropositionRepository propositions) {
        this.contrats = contrats; this.candidatures = candidatures; this.propositions = propositions;
    }

    public Contrat generer(Proposition reference) {
        Long candidatureId = propositions.trouverCandidatureId(reference.getId()).orElseThrow();
        Candidature c = candidatures.verrouiller(candidatureId).orElseThrow();
        Proposition p = propositions.findFirstByCandidatureIdOrderByNumeroVersionDesc(c.getId()).orElseThrow();
        if (!p.getId().equals(reference.getId()) || !p.isBailleurAccepte() || !p.isLocataireAccepte()) {
            throw new IllegalArgumentException("Le contrat exige le double accord sur la derniere proposition.");
        }
        return contrats.findByPropositionId(p.getId()).orElseGet(() -> {
            Contrat contrat = new Contrat();
            contrat.setProposition(p);
            contrat.setNumero("LB-" + UUID.randomUUID());
            contrat.setContenu(construireContenu(p));
            c.setStatut("ACCEPTEE");
            return contrats.save(contrat);
        });
    }

    @Transactional(readOnly = true)
    public Contrat consulter(Long id, Long utilisateurId) {
        Contrat c = contrats.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        PartiesContrat.verifier(c.getProposition().getCandidature(), utilisateurId);
        PartiesContrat.charger(c.getProposition().getCandidature());
        return c;
    }

    private String construireContenu(Proposition p) {
        Candidature c = p.getCandidature();
        Bien b = c.getAnnonce().getBien();
        return "CONTRAT DE BAIL\n\nENTRE LES SOUSSIGNES\nBailleur : " + identite(b.getBailleur())
                + "\nLocataire : " + identite(c.getLocataire())
                + "\n\n1. OBJET DU BAIL\nLe bailleur donne en location le bien situe a " + b.getAdresse()
                + ". Type : " + b.getTypeBien() + "; logement : " + b.getTypeLogement()
                + ". Usage du bien : " + b.getUsage() + ". Usage prevu : " + c.getUsagePrevu()
                + ". Nombre d'occupants : " + c.getNombreOccupants()
                + ".\nInventaire : " + texte(b.getInventaire())
                + "\n\n2. DUREE\nDebut : " + p.getDateDebut() + ". Fin : " + (p.getDateFin() == null ? "indeterminee" : p.getDateFin())
                + "\n\n3. CONDITIONS FINANCIERES\nLoyer mensuel : " + p.getLoyer() + " Ar. Charges : " + p.getCharges()
                + " Ar. Caution : " + p.getCaution() + " Ar. Avance : " + p.getAvance()
                + " Ar. Mode de paiement : " + texte(c.getAnnonce().getModePaiement())
                + "\n\n4. CONDITIONS PARTICULIERES\nSous-location : " + texte(p.getSousLocation())
                + "\n" + texte(p.getClausesSpeciales())
                + "\n\n5. ACCORD ET SIGNATURES\nLes parties ont accepte la proposition version " + p.getNumeroVersion()
                + ". Le present contrat est soumis a leur signature respective.";
    }
    private String identite(Utilisateur u) {
        return u.getNom() + " " + u.getPrenom() + ", piece : " + texte(u.getNumeroPiece())
                + ", email : " + u.getEmail() + ", telephone : " + texte(u.getTelephone());
    }
    private String texte(String valeur) { return valeur == null || valeur.isBlank() ? "Non renseigne" : valeur; }
}

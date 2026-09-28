package com.legatech.legabail.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "signature", uniqueConstraints = @UniqueConstraint(columnNames = {"contrat_id", "utilisateur_id"}))
public class Signature {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "contrat_id")
    private Contrat contrat;
    @ManyToOne(optional = false) @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @Column(name = "date_signature")
    private OffsetDateTime dateSignature;
    @Column(nullable = false, length = 15)
    private String statut = "SIGNEE";

    public Long getId() { return id; }
    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public OffsetDateTime getDateSignature() { return dateSignature; }
    public void setDateSignature(OffsetDateTime dateSignature) { this.dateSignature = dateSignature; }
    public String getStatut() { return statut; }
}

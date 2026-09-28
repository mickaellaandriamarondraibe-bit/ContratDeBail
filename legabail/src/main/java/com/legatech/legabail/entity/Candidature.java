package com.legatech.legabail.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "candidature", uniqueConstraints = @UniqueConstraint(columnNames = {"annonce_id", "locataire_id"}))
public class Candidature {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "annonce_id")
    private Annonce annonce;
    @ManyToOne(optional = false) @JoinColumn(name = "locataire_id")
    private Utilisateur locataire;
    @Column(name = "nombre_occupants", nullable = false)
    private Integer nombreOccupants;
    @Column(name = "usage_prevu", nullable = false, length = 50)
    private String usagePrevu;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Column(nullable = false, length = 20)
    private String statut = "ENVOYEE";
    @Column(name = "date_candidature", nullable = false)
    private OffsetDateTime dateCandidature = OffsetDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Annonce getAnnonce() { return annonce; }
    public void setAnnonce(Annonce annonce) { this.annonce = annonce; }
    public Utilisateur getLocataire() { return locataire; }
    public void setLocataire(Utilisateur locataire) { this.locataire = locataire; }
    public Integer getNombreOccupants() { return nombreOccupants; }
    public void setNombreOccupants(Integer nombreOccupants) { this.nombreOccupants = nombreOccupants; }
    public String getUsagePrevu() { return usagePrevu; }
    public void setUsagePrevu(String usagePrevu) { this.usagePrevu = usagePrevu; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public OffsetDateTime getDateCandidature() { return dateCandidature; }
}

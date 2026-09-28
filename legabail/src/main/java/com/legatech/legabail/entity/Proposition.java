package com.legatech.legabail.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "proposition", uniqueConstraints = @UniqueConstraint(columnNames = {"candidature_id", "numero_version"}))
public class Proposition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "candidature_id")
    private Candidature candidature;
    @Column(name = "numero_version", nullable = false)
    private int numeroVersion;
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal loyer;
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal charges;
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal caution;
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal avance;
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;
    @Column(name = "date_fin")
    private LocalDate dateFin;
    @Column(name = "sous_location", length = 30)
    private String sousLocation;
    @Column(name = "clauses_speciales", columnDefinition = "TEXT")
    private String clausesSpeciales;
    @Column(name = "bailleur_accepte", nullable = false)
    private boolean bailleurAccepte;
    @Column(name = "locataire_accepte", nullable = false)
    private boolean locataireAccepte;
    @Column(name = "date_creation", nullable = false)
    private OffsetDateTime dateCreation = OffsetDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }
    public int getNumeroVersion() { return numeroVersion; }
    public void setNumeroVersion(int numeroVersion) { this.numeroVersion = numeroVersion; }
    public BigDecimal getLoyer() { return loyer; }
    public void setLoyer(BigDecimal loyer) { this.loyer = loyer; }
    public BigDecimal getCharges() { return charges; }
    public void setCharges(BigDecimal charges) { this.charges = charges; }
    public BigDecimal getCaution() { return caution; }
    public void setCaution(BigDecimal caution) { this.caution = caution; }
    public BigDecimal getAvance() { return avance; }
    public void setAvance(BigDecimal avance) { this.avance = avance; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public String getSousLocation() { return sousLocation; }
    public void setSousLocation(String sousLocation) { this.sousLocation = sousLocation; }
    public String getClausesSpeciales() { return clausesSpeciales; }
    public void setClausesSpeciales(String clausesSpeciales) { this.clausesSpeciales = clausesSpeciales; }
    public boolean isBailleurAccepte() { return bailleurAccepte; }
    public void setBailleurAccepte(boolean bailleurAccepte) { this.bailleurAccepte = bailleurAccepte; }
    public boolean isLocataireAccepte() { return locataireAccepte; }
    public void setLocataireAccepte(boolean locataireAccepte) { this.locataireAccepte = locataireAccepte; }
    public OffsetDateTime getDateCreation() { return dateCreation; }
}

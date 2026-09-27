package com.legatech.legabail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "annonce")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal loyer;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal charges = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal caution = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal avance = BigDecimal.ZERO;

    @Column(name = "prix_logement_nu", precision = 15, scale = 2)
    private BigDecimal prixLogementNu;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "sous_location", length = 30)
    private String sousLocation;

    @Column(name = "mode_paiement", length = 50)
    private String modePaiement;

    @Column(name = "clauses_speciales", columnDefinition = "TEXT")
    private String clausesSpeciales;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutAnnonce statut = StatutAnnonce.BROUILLON;

    @Column(name = "date_creation", nullable = false)
    private OffsetDateTime dateCreation;

    public Annonce() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public BigDecimal getLoyer() {
        return loyer;
    }

    public void setLoyer(BigDecimal loyer) {
        this.loyer = loyer;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    public void setCharges(BigDecimal charges) {
        this.charges = charges;
    }

    public BigDecimal getCaution() {
        return caution;
    }

    public void setCaution(BigDecimal caution) {
        this.caution = caution;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public BigDecimal getPrixLogementNu() {
        return prixLogementNu;
    }

    public void setPrixLogementNu(BigDecimal prixLogementNu) {
        this.prixLogementNu = prixLogementNu;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getSousLocation() {
        return sousLocation;
    }

    public void setSousLocation(String sousLocation) {
        this.sousLocation = sousLocation;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getClausesSpeciales() {
        return clausesSpeciales;
    }

    public void setClausesSpeciales(String clausesSpeciales) {
        this.clausesSpeciales = clausesSpeciales;
    }

    public StatutAnnonce getStatut() {
        return statut;
    }

    public void setStatut(StatutAnnonce statut) {
        this.statut = statut;
    }

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Annonce other)) {
            return false;
        }
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

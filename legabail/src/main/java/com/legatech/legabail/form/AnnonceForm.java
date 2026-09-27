package com.legatech.legabail.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AnnonceForm {

    @NotNull(message = "Le bien est obligatoire.")
    private Long bienId;

    @NotNull(message = "Le loyer est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le loyer doit être supérieur à zéro.")
    private BigDecimal loyer;

    @NotNull(message = "Les charges sont obligatoires.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Les charges ne peuvent pas être négatives.")
    private BigDecimal charges = BigDecimal.ZERO;

    @NotNull(message = "La caution est obligatoire.")
    @DecimalMin(value = "0.00", inclusive = true, message = "La caution ne peut pas être négative.")
    private BigDecimal caution = BigDecimal.ZERO;

    @NotNull(message = "L'avance est obligatoire.")
    @DecimalMin(value = "0.00", inclusive = true, message = "L'avance ne peut pas être négative.")
    private BigDecimal avance = BigDecimal.ZERO;

    @DecimalMin(value = "0.00", inclusive = true, message = "Le prix du logement nu ne peut pas être négatif.")
    private BigDecimal prixLogementNu;

    @NotNull(message = "La date de début est obligatoire.")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String sousLocation;

    private String modePaiement;

    private String clausesSpeciales;

    public Long getBienId() {
        return bienId;
    }

    public void setBienId(Long bienId) {
        this.bienId = bienId;
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
}

package com.legatech.legabail.form;

import com.legatech.legabail.entity.Proposition;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class PropositionForm {
    @NotNull @Positive @Digits(integer = 13, fraction = 2)
    private BigDecimal loyer;
    @NotNull @PositiveOrZero @Digits(integer = 13, fraction = 2)
    private BigDecimal charges = BigDecimal.ZERO;
    @NotNull @PositiveOrZero @Digits(integer = 13, fraction = 2)
    private BigDecimal caution = BigDecimal.ZERO;
    @NotNull @PositiveOrZero @Digits(integer = 13, fraction = 2)
    private BigDecimal avance = BigDecimal.ZERO;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateDebut;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFin;
    @Size(max = 30)
    private String sousLocation;
    @Size(max = 10000)
    private String clausesSpeciales;

    @AssertTrue(message = "La fin du bail doit etre posterieure au debut.")
    public boolean isDatesValides() { return dateDebut == null || dateFin == null || dateFin.isAfter(dateDebut); }
    public static PropositionForm depuis(Proposition p) {
        PropositionForm f = new PropositionForm();
        f.loyer = p.getLoyer(); f.charges = p.getCharges(); f.caution = p.getCaution(); f.avance = p.getAvance();
        f.dateDebut = p.getDateDebut(); f.dateFin = p.getDateFin();
        f.sousLocation = p.getSousLocation(); f.clausesSpeciales = p.getClausesSpeciales();
        return f;
    }
    public void appliquer(Proposition p) {
        p.setLoyer(loyer); p.setCharges(charges); p.setCaution(caution); p.setAvance(avance);
        p.setDateDebut(dateDebut); p.setDateFin(dateFin); p.setSousLocation(sousLocation); p.setClausesSpeciales(clausesSpeciales);
    }
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
}

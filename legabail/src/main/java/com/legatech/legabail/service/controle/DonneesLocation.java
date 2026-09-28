package com.legatech.legabail.service.controle;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DonneesLocation {

    public enum TypeLogement {
        NU,
        MEUBLE
    }

    public enum Usage {
        HABITATION,
        PROFESSIONNEL_NON_COMMERCIAL,
        COMMERCIAL_INDUSTRIEL
    }

    public enum TypeDuree {
        DETERMINEE,
        INDETERMINEE
    }

    public enum SousLocation {
        INTERDITE,
        AUTORISATION_ECRITE,
        DEMANDEE_SANS_AUTORISATION
    }

    private TypeLogement typeLogement;
    private Usage usage;
    private TypeDuree typeDuree;
    private SousLocation sousLocation;

    private LocalDate datePermisHabiter;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private BigDecimal loyer;
    private BigDecimal charges;
    private BigDecimal caution;
    private BigDecimal avance;
    private BigDecimal prixLogementNu;

    private boolean hotelOuPension;
    private boolean logementLieEmploi;
    private boolean inventaireFourni;

    public TypeLogement getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(TypeLogement typeLogement) {
        this.typeLogement = typeLogement;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public TypeDuree getTypeDuree() {
        return typeDuree;
    }

    public void setTypeDuree(TypeDuree typeDuree) {
        this.typeDuree = typeDuree;
    }

    public SousLocation getSousLocation() {
        return sousLocation;
    }

    public void setSousLocation(SousLocation sousLocation) {
        this.sousLocation = sousLocation;
    }

    public LocalDate getDatePermisHabiter() {
        return datePermisHabiter;
    }

    public void setDatePermisHabiter(LocalDate datePermisHabiter) {
        this.datePermisHabiter = datePermisHabiter;
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

    public boolean isHotelOuPension() {
        return hotelOuPension;
    }

    public void setHotelOuPension(boolean hotelOuPension) {
        this.hotelOuPension = hotelOuPension;
    }

    public boolean isLogementLieEmploi() {
        return logementLieEmploi;
    }

    public void setLogementLieEmploi(boolean logementLieEmploi) {
        this.logementLieEmploi = logementLieEmploi;
    }

    public boolean isInventaireFourni() {
        return inventaireFourni;
    }

    public void setInventaireFourni(boolean inventaireFourni) {
        this.inventaireFourni = inventaireFourni;
    }
}
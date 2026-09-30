package com.legatech.legabail.form;

import com.legatech.legabail.entity.TypeLogement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Digits;
import java.time.LocalDate;
import java.math.BigDecimal;

public class BienForm {
    private java.util.List<String> photosAjoutees = new java.util.ArrayList<>();
    private java.util.List<String> photosSupprimees = new java.util.ArrayList<>();
    public java.util.List<String> getPhotosAjoutees() { return photosAjoutees; }
    public void setPhotosAjoutees(java.util.List<String> photos) { this.photosAjoutees = photos; }
    public java.util.List<String> getPhotosSupprimees() { return photosSupprimees; }
    public void setPhotosSupprimees(java.util.List<String> photos) { this.photosSupprimees = photos; }


    @NotBlank(message = "L'adresse est obligatoire.")
    private String adresse;

    @NotBlank(message = "Le type de bien est obligatoire.")
    @Size(max = 40, message = "Le type de bien ne doit pas dépasser 40 caractères.")
    private String typeBien;

    @NotBlank(message = "L'usage du bien est obligatoire.")
    @Size(max = 40, message = "L'usage ne doit pas dépasser 40 caractères.")
    private String usage;

    @NotNull(message = "Le type de logement est obligatoire.")
    private TypeLogement typeLogement;

    private LocalDate datePermisHabiter;

    private String inventaire;

    @Size(max = 500, message = "L'adresse de l'image ne doit pas dépasser 500 caractères.")
    private String imageUrl;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombrePieces;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreChambres;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreDouches;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreSallesBains;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreWc;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreCuisines;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer nombreEtages;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer etage;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    private Integer placesParking;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    @Digits(integer = 10, fraction = 2, message = "Saisissez une surface avec au maximum 2 décimales.")
    private BigDecimal surfaceHabitable;

    @PositiveOrZero(message = "La valeur doit être positive ou nulle.")
    @Digits(integer = 10, fraction = 2, message = "Saisissez une surface avec au maximum 2 décimales.")
    private BigDecimal surfaceTerrain;

    private Boolean cloture;

    private Boolean garage;

    private Boolean jardin;

    private Boolean balcon;

    private Boolean terrasse;

    private Boolean eauCourante;

    private Boolean electricite;

    @Size(max = 5000, message = "La description ne doit pas dépasser 5000 caractères.")
    private String description;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public TypeLogement getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(TypeLogement typeLogement) {
        this.typeLogement = typeLogement;
    }

    public LocalDate getDatePermisHabiter() {
        return datePermisHabiter;
    }

    public void setDatePermisHabiter(LocalDate datePermisHabiter) {
        this.datePermisHabiter = datePermisHabiter;
    }

    public String getInventaire() {
        return inventaire;
    }

    public void setInventaire(String inventaire) {
        this.inventaire = inventaire;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Integer getNombrePieces() { return nombrePieces; }

    public void setNombrePieces(Integer nombrePieces) { this.nombrePieces = nombrePieces; }

    public Integer getNombreChambres() { return nombreChambres; }

    public void setNombreChambres(Integer nombreChambres) { this.nombreChambres = nombreChambres; }

    public Integer getNombreDouches() { return nombreDouches; }

    public void setNombreDouches(Integer nombreDouches) { this.nombreDouches = nombreDouches; }

    public Integer getNombreSallesBains() { return nombreSallesBains; }

    public void setNombreSallesBains(Integer nombreSallesBains) { this.nombreSallesBains = nombreSallesBains; }

    public Integer getNombreWc() { return nombreWc; }

    public void setNombreWc(Integer nombreWc) { this.nombreWc = nombreWc; }

    public Integer getNombreCuisines() { return nombreCuisines; }

    public void setNombreCuisines(Integer nombreCuisines) { this.nombreCuisines = nombreCuisines; }

    public Integer getNombreEtages() { return nombreEtages; }

    public void setNombreEtages(Integer nombreEtages) { this.nombreEtages = nombreEtages; }

    public Integer getEtage() { return etage; }

    public void setEtage(Integer etage) { this.etage = etage; }

    public Integer getPlacesParking() { return placesParking; }

    public void setPlacesParking(Integer placesParking) { this.placesParking = placesParking; }

    public BigDecimal getSurfaceHabitable() { return surfaceHabitable; }

    public void setSurfaceHabitable(BigDecimal surfaceHabitable) { this.surfaceHabitable = surfaceHabitable; }

    public BigDecimal getSurfaceTerrain() { return surfaceTerrain; }

    public void setSurfaceTerrain(BigDecimal surfaceTerrain) { this.surfaceTerrain = surfaceTerrain; }

    public Boolean getCloture() { return cloture; }

    public void setCloture(Boolean cloture) { this.cloture = cloture; }

    public Boolean getGarage() { return garage; }

    public void setGarage(Boolean garage) { this.garage = garage; }

    public Boolean getJardin() { return jardin; }

    public void setJardin(Boolean jardin) { this.jardin = jardin; }

    public Boolean getBalcon() { return balcon; }

    public void setBalcon(Boolean balcon) { this.balcon = balcon; }

    public Boolean getTerrasse() { return terrasse; }

    public void setTerrasse(Boolean terrasse) { this.terrasse = terrasse; }

    public Boolean getEauCourante() { return eauCourante; }

    public void setEauCourante(Boolean eauCourante) { this.eauCourante = eauCourante; }

    public Boolean getElectricite() { return electricite; }

    public void setElectricite(Boolean electricite) { this.electricite = electricite; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}

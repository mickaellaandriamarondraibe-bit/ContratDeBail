package com.legatech.legabail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;
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
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bien")
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bailleur_id", nullable = false)
    private Utilisateur bailleur;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String adresse;

    @Column(name = "type_bien", nullable = false, length = 40)
    private String typeBien;

    @Column(nullable = false, length = 40)
    private String usage;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_logement", nullable = false, length = 15)
    private TypeLogement typeLogement;

    @Column(name = "date_permis_habiter")
    private LocalDate datePermisHabiter;

    @Column(columnDefinition = "TEXT")
    private String inventaire;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "nombre_pieces")
    private Integer nombrePieces;

    @Column(name = "nombre_chambres")
    private Integer nombreChambres;

    @Column(name = "nombre_douches")
    private Integer nombreDouches;

    @Column(name = "nombre_salles_bains")
    private Integer nombreSallesBains;

    @Column(name = "nombre_wc")
    private Integer nombreWc;

    @Column(name = "nombre_cuisines")
    private Integer nombreCuisines;

    @Column(name = "nombre_etages")
    private Integer nombreEtages;

    @Column(name = "etage")
    private Integer etage;

    @Column(name = "places_parking")
    private Integer placesParking;

    @Column(name = "surface_habitable", precision = 12, scale = 2)
    private BigDecimal surfaceHabitable;

    @Column(name = "surface_terrain", precision = 12, scale = 2)
    private BigDecimal surfaceTerrain;

    @Column(name = "cloture")
    private Boolean cloture;

    @Column(name = "garage")
    private Boolean garage;

    @Column(name = "jardin")
    private Boolean jardin;

    @Column(name = "balcon")
    private Boolean balcon;

    @Column(name = "terrasse")
    private Boolean terrasse;

    @Column(name = "eau_courante")
    private Boolean eauCourante;

    @Column(name = "electricite")
    private Boolean electricite;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bien_photo", joinColumns = @JoinColumn(name = "bien_id"))
    @OrderColumn(name = "position")
    @Column(name = "url", nullable = false, columnDefinition = "TEXT")
    private List<String> photos = new ArrayList<>();

    public List<String> getPhotos() { return photos; }

    public List<String> getGaleriePhotos() {
        List<String> galerie = new ArrayList<>();
        if (imageUrl != null && !imageUrl.isBlank()) galerie.add(imageUrl);
        for (String photo : photos) if (!galerie.contains(photo)) galerie.add(photo);
        return galerie;
    }

    public Bien() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getBailleur() {
        return bailleur;
    }

    public void setBailleur(Utilisateur bailleur) {
        this.bailleur = bailleur;
    }

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Bien other)) {
            return false;
        }
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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

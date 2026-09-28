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
import java.time.LocalDate;
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
}


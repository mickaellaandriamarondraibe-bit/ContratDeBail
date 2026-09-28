package com.legatech.legabail.form;

import com.legatech.legabail.entity.TypeLogement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class BienForm {

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
}


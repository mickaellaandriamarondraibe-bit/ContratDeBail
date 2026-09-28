package com.legatech.legabail.form;

import jakarta.validation.constraints.*;

public class LocataireForm {
    @NotBlank @Size(max = 100)
    private String nom;
    @NotBlank @Size(max = 100)
    private String prenom;
    @NotBlank @Email @Size(max = 180)
    private String email;
    @NotBlank @Size(min = 8, max = 255)
    private String motDePasse;
    @Size(max = 30)
    private String telephone;
    @Size(max = 100)
    private String numeroPiece;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getNumeroPiece() { return numeroPiece; }
    public void setNumeroPiece(String numeroPiece) { this.numeroPiece = numeroPiece; }
}

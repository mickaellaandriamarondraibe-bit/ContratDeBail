package com.legatech.legabail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article_juridique")
public class ArticleJuridique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String numero;

    @Column(nullable = false)
    private String titre;

    @Column(name = "texte_original", nullable = false, columnDefinition = "TEXT")
    private String texteOriginal;

    @Column(name = "explication_simple", nullable = false, columnDefinition = "TEXT")
    private String explicationSimple;

    @OneToMany(mappedBy = "articleJuridique")
    private List<Regle> regles = new ArrayList<>();

    public ArticleJuridique() {
    }

    public ArticleJuridique(
            String numero,
            String titre,
            String texteOriginal,
            String explicationSimple) {
        this.numero = numero;
        this.titre = titre;
        this.texteOriginal = texteOriginal;
        this.explicationSimple = explicationSimple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexteOriginal() {
        return texteOriginal;
    }

    public void setTexteOriginal(String texteOriginal) {
        this.texteOriginal = texteOriginal;
    }

    public String getExplicationSimple() {
        return explicationSimple;
    }

    public void setExplicationSimple(String explicationSimple) {
        this.explicationSimple = explicationSimple;
    }

    public List<Regle> getRegles() {
        return regles;
    }

    public void setRegles(List<Regle> regles) {
        this.regles = regles;
    }
}

package com.legatech.legabail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "regle")
public class Regle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleJuridique articleJuridique;

    @Column(nullable = false, unique = true, length = 100)
    private String code;

    @Column(nullable = false)
    private String titre;

    @Column(name = "explication_simple", nullable = false, columnDefinition = "TEXT")
    private String explicationSimple;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "condition_json", columnDefinition = "jsonb")
    private Map<String, Object> conditionJson = new HashMap<>();

    @Column(length = 100)
    private String controle;

    @Column(columnDefinition = "TEXT")
    private String consequence;

    @Column(nullable = false)
    private boolean bloquante = false;

    public Regle() {
    }

    public Regle(
            ArticleJuridique articleJuridique,
            String code,
            String titre,
            String explicationSimple,
            String controle,
            String consequence,
            boolean bloquante) {
        this.articleJuridique = articleJuridique;
        this.code = code;
        this.titre = titre;
        this.explicationSimple = explicationSimple;
        this.controle = controle;
        this.consequence = consequence;
        this.bloquante = bloquante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticleJuridique getArticleJuridique() {
        return articleJuridique;
    }

    public void setArticleJuridique(ArticleJuridique articleJuridique) {
        this.articleJuridique = articleJuridique;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getExplicationSimple() {
        return explicationSimple;
    }

    public void setExplicationSimple(String explicationSimple) {
        this.explicationSimple = explicationSimple;
    }

    public Map<String, Object> getConditionJson() {
        return conditionJson;
    }

    public void setConditionJson(Map<String, Object> conditionJson) {
        this.conditionJson = conditionJson;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public boolean isBloquante() {
        return bloquante;
    }

    public void setBloquante(boolean bloquante) {
        this.bloquante = bloquante;
    }
}

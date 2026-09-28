package com.legatech.legabail.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "contrat")
public class Contrat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false) @JoinColumn(name = "proposition_id", unique = true)
    private Proposition proposition;
    @Column(nullable = false, unique = true, length = 50)
    private String numero;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenu;
    @Column(name = "fichier_pdf", columnDefinition = "TEXT")
    private String fichierPdf;
    @Column(nullable = false, length = 20)
    private String statut = "A_SIGNER";
    @Column(name = "date_generation", nullable = false)
    private OffsetDateTime dateGeneration = OffsetDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Proposition getProposition() { return proposition; }
    public void setProposition(Proposition proposition) { this.proposition = proposition; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public String getFichierPdf() { return fichierPdf; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public OffsetDateTime getDateGeneration() { return dateGeneration; }
}

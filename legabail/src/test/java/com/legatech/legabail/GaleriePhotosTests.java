package com.legatech.legabail;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.repository.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GaleriePhotosTests {
    static final Path dossier;
    static {
        try { dossier = Files.createTempDirectory("legabail-galerie-test-"); }
        catch (java.io.IOException e) { throw new java.io.UncheckedIOException(e); }
    }
    @DynamicPropertySource
    static void configuration(DynamicPropertyRegistry registry) {
        registry.add("legabail.images.directory", dossier::toString);
    }
    @Autowired MockMvc mvc;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired BienRepository biens;
    @Autowired AnnonceRepository annonces;

    private MockHttpSession session() {
        Utilisateur bailleur = new Utilisateur();
        bailleur.setRole(RoleUtilisateur.BAILLEUR);
        bailleur.setNom("Test"); bailleur.setPrenom("Photos");
        bailleur.setEmail(UUID.randomUUID() + "@galerie.test");
        bailleur.setMotDePasseHash("test");
        utilisateurs.save(bailleur);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("bailleurId", bailleur.getId());
        return session;
    }
    private MockMultipartFile photo(String nom) {
        return new MockMultipartFile("image", nom, "image/png", new byte[]{(byte)0x89,0x50,0x4e,0x47,0x0d,0x0a,0x1a,0x0a});
    }
    private MockMultipartHttpServletRequestBuilder formulaire(String url, MockHttpSession session) {
        var request = multipart(url);
        request.session(session).param("adresse", "Maison avec galerie").param("typeBien", "Maison")
                .param("usage", "HABITATION").param("typeLogement", "NU");
        return request;
    }
    @Test
    void ajouteConserveEtRetireLesPhotosEtAfficheLeCarrousel() throws Exception {
        var session = session();
        mvc.perform(formulaire("/bailleur/biens", session).file(photo("batiment.png"))
                        .file(photo("chambre.png")).file(photo("douche.png")))
                .andExpect(status().is3xxRedirection());
        Bien bien = biens.findByBailleurId((Long) session.getAttribute("bailleurId")).getFirst();
        assertEquals(3, bien.getGaleriePhotos().size());
        String couverture = bien.getImageUrl();
        String chambre = bien.getGaleriePhotos().get(1);
        assertTrue(Files.exists(dossier.resolve(Path.of(chambre).getFileName())));
        mvc.perform(get("/bailleur/biens/" + bien.getId() + "/modifier").session(session))
                .andExpect(status().isOk()).andExpect(content().string(containsString(chambre)));
        Annonce annonce = new Annonce(); annonce.setBien(bien);
        annonce.setLoyer(new BigDecimal("500000")); annonce.setCharges(BigDecimal.ZERO);
        annonce.setCaution(BigDecimal.ZERO); annonce.setAvance(BigDecimal.ZERO);
        annonce.setDateDebut(LocalDate.now()); annonce.setDateCreation(OffsetDateTime.now());
        annonce.setStatut(StatutAnnonce.PUBLIEE); annonces.save(annonce);
        for (String url : new String[]{"/annonces", "/annonces/" + annonce.getId()}) {
            mvc.perform(get(url)).andExpect(status().isOk())
                    .andExpect(content().string(containsString("1 / 3")))
                    .andExpect(content().string(containsString(chambre)))
                    .andExpect(content().string(containsString("Photo suivante")));
        }
        mvc.perform(formulaire("/bailleur/biens/" + bien.getId(), session))
                .andExpect(status().is3xxRedirection());
        assertEquals(3, biens.findById(bien.getId()).orElseThrow().getGaleriePhotos().size());
        mvc.perform(formulaire("/bailleur/biens/" + bien.getId(), session)
                        .file(photo("jardin.png")).param("photosSupprimees", couverture))
                .andExpect(status().is3xxRedirection());
        Bien modifie = biens.findById(bien.getId()).orElseThrow();
        assertEquals(3, modifie.getGaleriePhotos().size());
        assertEquals(chambre, modifie.getImageUrl());
        assertFalse(modifie.getGaleriePhotos().contains(couverture));
    }
    @Test
    void refuseLotInvalideEtTropDePhotosSansEcrireDeFichiers() throws Exception {
        var session = session();
        long avant;
        try (var fichiers = Files.list(dossier)) { avant = fichiers.count(); }
        mvc.perform(formulaire("/bailleur/biens", session).file(photo("ok.png"))
                        .file(new MockMultipartFile("image", "faux.png", "image/png", new byte[]{1,2,3})))
                .andExpect(status().isOk()).andExpect(model().attributeHasFieldErrors("bienForm", "imageUrl"));
        var requete = formulaire("/bailleur/biens", session);
        for (int i = 0; i < 11; i++) requete.file(photo("photo.png"));
        mvc.perform(requete).andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("bienForm", "imageUrl"));
        try (var fichiers = Files.list(dossier)) { assertEquals(avant, fichiers.count()); }
        assertTrue(biens.findByBailleurId((Long) session.getAttribute("bailleurId")).isEmpty());
    }
    @Test
    void conserveLaPhotoDesAnciensBiensEtIgnoreLesUrlsInjectees() throws Exception {
        var session = session();
        mvc.perform(formulaire("/bailleur/biens", session).param("photosAjoutees[0]", "https://invalide.test/photo")
                        .param("imageUrl", "https://invalide.test/photo"))
                .andExpect(status().is3xxRedirection());
        Bien bien = biens.findByBailleurId((Long) session.getAttribute("bailleurId")).getFirst();
        assertTrue(bien.getGaleriePhotos().isEmpty());
        bien.setImageUrl("/images/biens/image.png"); biens.save(bien);
        mvc.perform(formulaire("/bailleur/biens/" + bien.getId(), session))
                .andExpect(status().is3xxRedirection());
        assertEquals(java.util.List.of("/images/biens/image.png"), biens.findById(bien.getId()).orElseThrow().getGaleriePhotos());
    }
}

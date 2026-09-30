package com.legatech.legabail;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BienDetailsTests {
    @Autowired MockMvc mvc;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired BienRepository biens;
    @Autowired AnnonceRepository annonces;

    private MockHttpSession session() {
        Utilisateur bailleur = new Utilisateur();
        bailleur.setRole(RoleUtilisateur.BAILLEUR);
        bailleur.setNom("Test");
        bailleur.setPrenom("Bailleur");
        bailleur.setEmail(UUID.randomUUID() + "@details.test");
        bailleur.setMotDePasseHash("test");
        utilisateurs.save(bailleur);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("bailleurId", bailleur.getId());
        return session;
    }

    private MockHttpServletRequestBuilder formulaire(String url, MockHttpSession session) {
        return post(url).session(session).param("adresse", "Maison détaillée")
                .param("typeBien", "Maison").param("usage", "HABITATION").param("typeLogement", "NU");
    }

    @Test
    void creeModifieEtAfficheLesDetails() throws Exception {
        MockHttpSession session = session();
        var requete = formulaire("/bailleur/biens", session)
                .param("surfaceHabitable", "125.50").param("surfaceTerrain", "400.25")
                .param("description", "Maison lumineuse avec dépendance");
        String[] nombres = {"nombrePieces", "nombreChambres", "nombreDouches", "nombreSallesBains",
                "nombreWc", "nombreCuisines", "nombreEtages", "etage", "placesParking"};
        String[] equipements = {"cloture", "garage", "jardin", "balcon", "terrasse", "eauCourante", "electricite"};
        for (String nom : nombres) requete.param(nom, "2");
        for (String nom : equipements) requete.param(nom, "true");
        mvc.perform(requete).andExpect(status().is3xxRedirection());
        Bien bien = biens.findByBailleurId((Long) session.getAttribute("bailleurId")).getFirst();
        for (String nom : nombres) assertEquals(2, Bien.class.getMethod("get" + Character.toUpperCase(nom.charAt(0)) + nom.substring(1)).invoke(bien));
        for (String nom : equipements) assertEquals(true, Bien.class.getMethod("get" + Character.toUpperCase(nom.charAt(0)) + nom.substring(1)).invoke(bien));
        assertEquals(new BigDecimal("125.50"), bien.getSurfaceHabitable());
        assertEquals(new BigDecimal("400.25"), bien.getSurfaceTerrain());
        mvc.perform(get("/bailleur/biens/" + bien.getId() + "/modifier").session(session))
                .andExpect(status().isOk()).andExpect(content().string(containsString("125.50")))
                .andExpect(content().string(containsString("Maison lumineuse avec d&eacute;pendance")));
        mvc.perform(get("/espace-bailleur").session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Maison lumineuse avec dépendance")));

        Annonce annonce = new Annonce();
        annonce.setBien(bien);
        annonce.setLoyer(new BigDecimal("500000"));
        annonce.setCharges(BigDecimal.ZERO);
        annonce.setCaution(BigDecimal.ZERO);
        annonce.setAvance(BigDecimal.ZERO);
        annonce.setDateDebut(LocalDate.now());
        annonce.setDateCreation(OffsetDateTime.now());
        annonce.setStatut(StatutAnnonce.PUBLIEE);
        annonces.save(annonce);
        mvc.perform(get("/annonces/" + annonce.getId())).andExpect(status().isOk())
                .andExpect(content().string(containsString("Terrain clôturé")))
                .andExpect(content().string(containsString("Maison lumineuse avec dépendance")));

        mvc.perform(formulaire("/bailleur/biens/" + bien.getId(), session)
                        .param("nombreDouches", "0").param("cloture", "false"))
                .andExpect(status().is3xxRedirection());
        Bien modifie = biens.findById(bien.getId()).orElseThrow();
        assertEquals(0, modifie.getNombreDouches());
        assertEquals(false, modifie.getCloture());
        assertNull(modifie.getSurfaceHabitable());
        assertNull(modifie.getGarage());
    }

    @Test
    void refuseValeursNegativesEtAccepteChampsFacultatifs() throws Exception {
        MockHttpSession session = session();
        mvc.perform(formulaire("/bailleur/biens", session)
                        .param("nombrePieces", "-1").param("surfaceHabitable", "-3"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("bienForm", "nombrePieces", "surfaceHabitable"));
        assertTrue(biens.findByBailleurId((Long) session.getAttribute("bailleurId")).isEmpty());
        mvc.perform(formulaire("/bailleur/biens", session)).andExpect(status().is3xxRedirection());
        mvc.perform(get("/espace-bailleur").session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Non renseigné")));
    }
}

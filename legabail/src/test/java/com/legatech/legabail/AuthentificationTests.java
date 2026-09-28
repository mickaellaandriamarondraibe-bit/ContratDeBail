package com.legatech.legabail;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.repository.UtilisateurRepository;
import com.legatech.legabail.service.MotDePasseService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class AuthentificationTests {

    @Autowired MockMvc mvc;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired MotDePasseService motsDePasse;

    @Test
    void afficheLaPageDeConnexion() throws Exception {
        mvc.perform(get("/connexion"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Se connecter")));
    }

    @Test
    void connecteUnLocataireAvecUnMotDePasseHache() throws Exception {
        Utilisateur locataire = utilisateur(RoleUtilisateur.LOCATAIRE, true);

        MvcResult resultat = mvc.perform(post("/connexion")
                        .param("email", locataire.getEmail().toUpperCase())
                        .param("motDePasse", "motdepasse-test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/locataire/candidatures"))
                .andReturn();

        assertTrue(resultat.getRequest().getSession().getAttribute("locataireId") instanceof Long);
    }

    @Test
    void connecteUnAncienCompteBailleurEtRefuseUnMauvaisMotDePasse() throws Exception {
        Utilisateur bailleur = utilisateur(RoleUtilisateur.BAILLEUR, false);

        mvc.perform(post("/connexion")
                        .param("email", bailleur.getEmail())
                        .param("motDePasse", "incorrect"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Adresse email ou mot de passe incorrect")));

        MvcResult resultat = mvc.perform(post("/connexion")
                        .param("email", bailleur.getEmail())
                        .param("motDePasse", "motdepasse-test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/espace-bailleur"))
                .andReturn();

        assertTrue(resultat.getRequest().getSession().getAttribute("bailleurId") instanceof Long);
    }

    private Utilisateur utilisateur(RoleUtilisateur role, boolean hache) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRole(role);
        utilisateur.setNom("Test");
        utilisateur.setPrenom(role.name());
        utilisateur.setEmail(UUID.randomUUID() + "@connexion.test");
        utilisateur.setMotDePasseHash(hache
                ? motsDePasse.hacher("motdepasse-test")
                : "motdepasse-test");
        return utilisateurs.save(utilisateur);
    }
}

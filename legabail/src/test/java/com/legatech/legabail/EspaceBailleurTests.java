package com.legatech.legabail;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.legatech.legabail.entity.Annonce;
import com.legatech.legabail.entity.Bien;
import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.StatutAnnonce;
import com.legatech.legabail.entity.TypeLogement;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.repository.AnnonceRepository;
import com.legatech.legabail.repository.BienRepository;
import com.legatech.legabail.repository.UtilisateurRepository;
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

@SpringBootTest
@AutoConfigureMockMvc
class EspaceBailleurTests {

    @Autowired MockMvc mvc;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired BienRepository biens;
    @Autowired AnnonceRepository annonces;

    @Test
    void afficheAdresseDuBienPourUneAnnonceDetachee() throws Exception {
        Utilisateur bailleur = new Utilisateur();
        bailleur.setRole(RoleUtilisateur.BAILLEUR);
        bailleur.setNom("Test");
        bailleur.setPrenom("Bailleur");
        bailleur.setEmail(UUID.randomUUID() + "@espace-bailleur.test");
        bailleur.setMotDePasseHash("test-only");
        utilisateurs.save(bailleur);

        Bien bien = new Bien();
        bien.setBailleur(bailleur);
        bien.setAdresse("Adresse chargee avec annonce");
        bien.setTypeBien("Appartement");
        bien.setUsage("HABITATION");
        bien.setTypeLogement(TypeLogement.NU);
        biens.save(bien);

        Annonce annonce = new Annonce();
        annonce.setBien(bien);
        annonce.setLoyer(new BigDecimal("500000"));
        annonce.setCharges(BigDecimal.ZERO);
        annonce.setCaution(BigDecimal.ZERO);
        annonce.setAvance(BigDecimal.ZERO);
        annonce.setDateDebut(LocalDate.of(2027, 1, 1));
        annonce.setDateCreation(OffsetDateTime.now());
        annonce.setStatut(StatutAnnonce.BROUILLON);
        annonces.save(annonce);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("bailleurId", bailleur.getId());

        mvc.perform(get("/espace-bailleur").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Adresse chargee avec annonce")));
    }
}

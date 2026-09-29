package com.legatech.legabail;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.legatech.legabail.entity.Annonce;
import com.legatech.legabail.entity.Bien;
import com.legatech.legabail.entity.Candidature;
import com.legatech.legabail.entity.Contrat;
import com.legatech.legabail.entity.Proposition;
import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.StatutAnnonce;
import com.legatech.legabail.entity.TypeLogement;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.repository.AnnonceRepository;
import com.legatech.legabail.repository.BienRepository;
import com.legatech.legabail.repository.CandidatureRepository;
import com.legatech.legabail.repository.ContratRepository;
import com.legatech.legabail.repository.PropositionRepository;
import com.legatech.legabail.repository.UtilisateurRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ContratFiltreTests {

    @Autowired MockMvc mvc;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired BienRepository biens;
    @Autowired AnnonceRepository annonces;
    @Autowired CandidatureRepository candidatures;
    @Autowired PropositionRepository propositions;
    @Autowired ContratRepository contrats;

    @Test
    void filtreParVilleDateEtStatut() throws Exception {
        Utilisateur bailleur = utilisateur(RoleUtilisateur.BAILLEUR, "Bailleur filtre");
        contrat(bailleur, "Antananarivo, Analakely", LocalDate.of(2026, 3, 10), "A_SIGNER");
        contrat(bailleur, "Toamasina, Centre", LocalDate.of(2026, 8, 20), "SIGNE");
        MockHttpSession session = session(bailleur);

        mvc.perform(get("/bailleur/contrats").session(session)
                        .param("ville", "Analakely")
                        .param("dateDebut", "2026-03-01")
                        .param("dateFin", "2026-03-31")
                        .param("statut", "A_SIGNER"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Antananarivo, Analakely")))
                .andExpect(content().string(not(containsString("Toamasina, Centre"))))
                .andExpect(content().string(containsString("1 contrat(s) trouvé(s) sur 2")));
    }

    @Test
    void filtreSansResultatEtIsolationEntreBailleurs() throws Exception {
        Utilisateur bailleur = utilisateur(RoleUtilisateur.BAILLEUR, "Bailleur principal");
        Utilisateur autreBailleur = utilisateur(RoleUtilisateur.BAILLEUR, "Autre bailleur");
        contrat(bailleur, "Fianarantsoa, Centre", LocalDate.of(2026, 4, 5), "SIGNE");
        contrat(autreBailleur, "Antsirabe, Centre", LocalDate.of(2026, 4, 5), "SIGNE");
        MockHttpSession session = session(bailleur);

        mvc.perform(get("/bailleur/contrats").session(session).param("ville", "Inexistante"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0 contrat(s) trouvé(s) sur 1")));

        mvc.perform(get("/bailleur/contrats").session(session))
                .andExpect(content().string(containsString("Fianarantsoa, Centre")))
                .andExpect(content().string(not(containsString("Antsirabe, Centre"))));
    }

    @Test
    void filtreAgeEtNaissanceEtIgnoreDatesInconnues() throws Exception {
        Utilisateur bailleur = utilisateur(RoleUtilisateur.BAILLEUR, "Bailleur âge");
        Contrat cible = contrat(bailleur, "Adresse cible", LocalDate.now(), "SIGNE");
        contrat(bailleur, "Naissance inconnue", LocalDate.now(), "SIGNE");
        Utilisateur locataire = cible.getProposition().getCandidature().getLocataire();
        LocalDate naissance = LocalDate.now().minusYears(25);
        locataire.setDateNaissance(naissance);
        utilisateurs.save(locataire);
        for (String critere : new String[]{"age", "dateNaissance"}) {
            mvc.perform(get("/bailleur/contrats").session(session(bailleur))
                    .param(critere, critere.equals("age") ? "25" : naissance.toString()))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Adresse cible")))
                    .andExpect(content().string(not(containsString("Naissance inconnue"))));
        }
        mvc.perform(get("/bailleur/contrats").session(session(bailleur)).param("age", "24"))
                .andExpect(content().string(containsString("0 contrat(s) trouvé(s) sur 2")));
    }

    private Contrat contrat(Utilisateur bailleur, String adresse, LocalDate date, String statut) {
        Utilisateur locataire = utilisateur(RoleUtilisateur.LOCATAIRE, "Locataire filtre");
        Bien bien = new Bien();
        bien.setBailleur(bailleur);
        bien.setAdresse(adresse);
        bien.setTypeBien("Appartement");
        bien.setUsage("HABITATION");
        bien.setTypeLogement(TypeLogement.NU);
        biens.save(bien);

        Annonce annonce = new Annonce();
        annonce.setBien(bien);
        annonce.setLoyer(new BigDecimal("750000"));
        annonce.setDateDebut(LocalDate.of(2026, 1, 1));
        annonce.setDateCreation(date.atStartOfDay().atOffset(ZoneOffset.UTC));
        annonce.setStatut(StatutAnnonce.PUBLIEE);
        annonces.save(annonce);

        Candidature candidature = new Candidature();
        candidature.setAnnonce(annonce);
        candidature.setLocataire(locataire);
        candidature.setNombreOccupants(1);
        candidature.setUsagePrevu("HABITATION");
        candidatures.save(candidature);

        Proposition proposition = new Proposition();
        proposition.setCandidature(candidature);
        proposition.setNumeroVersion(1);
        proposition.setLoyer(annonce.getLoyer());
        proposition.setCharges(BigDecimal.ZERO);
        proposition.setCaution(BigDecimal.ZERO);
        proposition.setAvance(BigDecimal.ZERO);
        proposition.setDateDebut(annonce.getDateDebut());
        propositions.save(proposition);

        Contrat contrat = new Contrat();
        contrat.setProposition(proposition);
        contrat.setNumero("TEST-" + UUID.randomUUID());
        contrat.setContenu("Contrat de test");
        contrat.setStatut(statut);
        contrat.setDateGeneration(date.atStartOfDay().atOffset(ZoneOffset.UTC));
        return contrats.save(contrat);
    }

    private MockHttpSession session(Utilisateur bailleur) {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("bailleurId", bailleur.getId());
        return session;
    }

    private Utilisateur utilisateur(RoleUtilisateur role, String nom) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRole(role);
        utilisateur.setNom(nom);
        utilisateur.setPrenom("Test");
        utilisateur.setEmail(UUID.randomUUID() + "@filtre.test");
        utilisateur.setMotDePasseHash("test-only");
        return utilisateurs.save(utilisateur);
    }
}

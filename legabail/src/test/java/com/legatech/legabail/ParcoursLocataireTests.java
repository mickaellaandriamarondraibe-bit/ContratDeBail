package com.legatech.legabail;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.*;
import com.legatech.legabail.repository.*;
import com.legatech.legabail.service.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class ParcoursLocataireTests {
    @Autowired CandidatureService candidatures;
    @Autowired PropositionService propositions;
    @Autowired ContratService contrats;
    @Autowired SignatureService signatures;
    @Autowired UtilisateurRepository utilisateurs;
    @Autowired BienRepository biens;
    @Autowired AnnonceRepository annonces;
    @Autowired PropositionRepository propositionRepository;
    @Autowired ContratRepository contratRepository;
    @Autowired CandidatureRepository candidatureRepository;
    @Autowired SignatureRepository signatureRepository;
    @Autowired MockMvc mvc;
    Utilisateur bailleur, locataire;
    Annonce annonce;
    Candidature candidature;
    Proposition proposition;

    @BeforeEach
    void preparer() {
        bailleur = utilisateur(RoleUtilisateur.BAILLEUR);
        locataire = utilisateur(RoleUtilisateur.LOCATAIRE);
        Bien bien = new Bien(); bien.setBailleur(bailleur); bien.setAdresse("12 rue du Lac, Antananarivo");
        bien.setTypeBien("Appartement"); bien.setUsage("HABITATION"); bien.setTypeLogement(TypeLogement.NU);
        biens.save(bien);
        annonce = new Annonce(); annonce.setBien(bien); annonce.setLoyer(new BigDecimal("750000.00"));
        annonce.setCharges(new BigDecimal("50000.00")); annonce.setDateDebut(LocalDate.of(2027, 1, 1));
        annonce.setStatut(StatutAnnonce.PUBLIEE); annonce.setDateCreation(OffsetDateTime.now()); annonces.save(annonce);
        CandidatureForm f = new CandidatureForm(); f.setNombreOccupants(2); f.setUsagePrevu("HABITATION");
        candidature = candidatures.enregistrer(annonce.getId(), locataire.getId(), f);
        proposition = propositionRepository.findFirstByCandidatureIdOrderByNumeroVersionDesc(candidature.getId()).orElseThrow();
    }

    private Utilisateur utilisateur(RoleUtilisateur role) {
        Utilisateur u = new Utilisateur(); u.setRole(role); u.setNom("Rakoto"); u.setPrenom(role.name());
        u.setEmail(UUID.randomUUID() + "@example.test"); u.setMotDePasseHash("test-only"); return utilisateurs.save(u);
    }
    private MockHttpSession session(Utilisateur u) {
        MockHttpSession s = new MockHttpSession();
        s.setAttribute(u.getRole() == RoleUtilisateur.BAILLEUR ? "bailleurId" : "locataireId", u.getId());
        s.setAttribute("csrfLocataire", "jeton-test"); return s;
    }
    private Contrat doubleAccord() {
        propositions.accepter(proposition.getId(), bailleur.getId(), RoleUtilisateur.BAILLEUR);
        propositions.accepter(proposition.getId(), locataire.getId(), RoleUtilisateur.LOCATAIRE);
        return contratRepository.findByPropositionId(proposition.getId()).orElseThrow();
    }

    @Test void candidatureEtDoublon() {
        assertEquals("ENVOYEE", candidature.getStatut());
        assertEquals(1, proposition.getNumeroVersion());
        assertEquals(annonce.getLoyer(), proposition.getLoyer());
        CandidatureForm f = new CandidatureForm(); f.setUsagePrevu("HABITATION");
        assertThrows(IllegalArgumentException.class, () -> candidatures.enregistrer(annonce.getId(), locataire.getId(), f));
    }
    @Test void accordBailleurSeulNeGenerePasDeContrat() {
        propositions.accepter(proposition.getId(), bailleur.getId(), RoleUtilisateur.BAILLEUR);
        assertTrue(contratRepository.findByPropositionId(proposition.getId()).isEmpty());
        Proposition p = propositionRepository.findById(proposition.getId()).orElseThrow();
        assertThrows(IllegalArgumentException.class, () -> contrats.generer(p));
    }
    @Test void accordLocataireSeulNeGenerePasDeContrat() {
        propositions.accepter(proposition.getId(), locataire.getId(), RoleUtilisateur.LOCATAIRE);
        assertTrue(contratRepository.findByPropositionId(proposition.getId()).isEmpty());
    }
    @Test void aucunAccordNeGenerePasDeContrat() {
        proposition.setBailleurAccepte(true);
        proposition.setLocataireAccepte(true);
        assertThrows(IllegalArgumentException.class, () -> contrats.generer(proposition));
    }
    @Test void nouvelleVersionReinitialiseAccordsEtRefuseAncienne() {
        propositions.accepter(proposition.getId(), bailleur.getId(), RoleUtilisateur.BAILLEUR);
        PropositionForm f = PropositionForm.depuis(proposition); f.setLoyer(new BigDecimal("700000"));
        Proposition p = propositions.modifier(proposition.getId(), locataire.getId(), f);
        assertEquals(2, p.getNumeroVersion()); assertFalse(p.isBailleurAccepte()); assertFalse(p.isLocataireAccepte());
        assertEquals("NEGOCIATION", candidatureRepository.findById(candidature.getId()).orElseThrow().getStatut());
        assertThrows(IllegalArgumentException.class, () -> propositions.accepter(proposition.getId(), locataire.getId(), RoleUtilisateur.LOCATAIRE));
        propositions.accepter(p.getId(), locataire.getId(), RoleUtilisateur.LOCATAIRE);
        assertTrue(contratRepository.findByPropositionId(p.getId()).isEmpty());
        propositions.accepter(p.getId(), bailleur.getId(), RoleUtilisateur.BAILLEUR);
        assertTrue(contratRepository.findByPropositionId(p.getId()).isPresent());
    }
    @Test void contratEtDeuxSignatures() {
        Contrat contrat = doubleAccord();
        assertTrue(contrat.getNumero().startsWith("LB-"));
        assertTrue(contrat.getContenu().contains("750000")); assertTrue(contrat.getContenu().contains("12 rue du Lac"));
        assertEquals("ACCEPTEE", candidatureRepository.findById(candidature.getId()).orElseThrow().getStatut());
        SignatureForm f = new SignatureForm(); f.setConfirmation(true);
        signatures.signer(contrat.getId(), bailleur.getId(), f);
        assertEquals("A_SIGNER", contratRepository.findById(contrat.getId()).orElseThrow().getStatut());
        signatures.signer(contrat.getId(), locataire.getId(), f);
        signatures.signer(contrat.getId(), locataire.getId(), f);
        assertEquals("SIGNE", contratRepository.findById(contrat.getId()).orElseThrow().getStatut());
        assertEquals(2, signatureRepository.findByContratIdOrderByDateSignatureAsc(contrat.getId()).size());
        assertThrows(IllegalArgumentException.class, () -> propositions.modifier(proposition.getId(), locataire.getId(), PropositionForm.depuis(proposition)));
    }
    @Test void interditAuxTiersEtMauvaisRole() {
        Long tiers = utilisateur(RoleUtilisateur.LOCATAIRE).getId();
        assertThrows(ResponseStatusException.class, () -> candidatures.consulter(candidature.getId(), tiers));
        assertThrows(ResponseStatusException.class, () -> propositions.accepter(proposition.getId(), locataire.getId(), RoleUtilisateur.BAILLEUR));
        Contrat contrat = doubleAccord(); SignatureForm f = new SignatureForm(); f.setConfirmation(true);
        assertThrows(ResponseStatusException.class, () -> signatures.signer(contrat.getId(), tiers, f));
    }
    @Test void refuseDatesEtMontantsInvalides() {
        PropositionForm f = PropositionForm.depuis(proposition); f.setDateFin(f.getDateDebut().minusDays(1));
        assertThrows(jakarta.validation.ConstraintViolationException.class, () -> propositions.modifier(proposition.getId(), locataire.getId(), f));
        f.setDateFin(null); f.setLoyer(BigDecimal.ZERO);
        assertThrows(jakarta.validation.ConstraintViolationException.class, () -> propositions.modifier(proposition.getId(), locataire.getId(), f));
    }
    @Test void pagesEtParcoursMvc() throws Exception {
        MockHttpSession l = session(locataire), b = session(bailleur);
        mvc.perform(get("/annonces")).andExpect(status().isOk()).andExpect(content().string(containsString("12 rue du Lac")));
        mvc.perform(get("/annonces/{id}", annonce.getId())).andExpect(status().isOk()).andExpect(content().string(containsString("Candidater")));
        mvc.perform(get("/inscription/locataire")).andExpect(status().isOk()).andExpect(content().string(containsString("Creer mon compte")));
        mvc.perform(get("/annonces/{id}/candidater", annonce.getId()).session(l)).andExpect(status().isOk());
        mvc.perform(get("/locataire/candidatures").session(l)).andExpect(status().isOk()).andExpect(content().string(containsString("12 rue du Lac")));
        mvc.perform(get("/bailleur/candidatures").session(b)).andExpect(status().isOk());
        mvc.perform(get("/candidatures/{id}", candidature.getId()).session(l)).andExpect(status().isOk()).andExpect(content().string(containsString("Accepter les conditions")));
        mvc.perform(get("/candidatures/{id}/negociation", candidature.getId()).session(l)).andExpect(status().isOk())
                .andExpect(content().string(containsString("value=\"2027-01-01\"")));
        mvc.perform(post("/propositions/{id}/accepter-locataire", proposition.getId()).session(l)).andExpect(status().isForbidden());
        mvc.perform(post("/propositions/{id}/accepter-locataire", proposition.getId()).session(l).param("_csrf", "jeton-test")).andExpect(status().is3xxRedirection());
        mvc.perform(post("/propositions/{id}/accepter-bailleur", proposition.getId()).session(b).param("_csrf", "jeton-test")).andExpect(status().is3xxRedirection());
        Contrat c = contratRepository.findByPropositionId(proposition.getId()).orElseThrow();
        mvc.perform(get("/contrats/{id}", c.getId()).session(l)).andExpect(status().isOk()).andExpect(content().string(containsString("Signer le contrat")));
        mvc.perform(post("/contrats/{id}/signer", c.getId()).session(l).param("_csrf", "jeton-test").param("confirmation", "false")).andExpect(status().isOk()).andExpect(content().string(containsString("Vous devez confirmer")));
        mvc.perform(post("/contrats/{id}/signer", c.getId()).session(l).param("_csrf", "jeton-test").param("confirmation", "true")).andExpect(status().is3xxRedirection());
        mvc.perform(get("/contrats/{id}/imprimer", c.getId()).session(b)).andExpect(status().isOk()).andExpect(content().string(containsString("window.print()")));
    }
    @Test void inscriptionEtValidationMvc() throws Exception {
        MockHttpSession s = new MockHttpSession();
        mvc.perform(get("/inscription/locataire").session(s)).andExpect(status().isOk());
        String jeton = (String) s.getAttribute("csrfLocataire");
        mvc.perform(post("/inscription/locataire").session(s).param("_csrf", jeton).param("email", "invalide")).andExpect(status().isOk());
        String email = UUID.randomUUID() + "@example.test";
        mvc.perform(post("/inscription/locataire").session(s).param("_csrf", jeton).param("nom", "Test").param("prenom", "Alice")
                .param("email", email).param("motDePasse", "motdepasse-test")).andExpect(redirectedUrl("/locataire/candidatures"));
        assertNotNull(s.getAttribute("locataireId"));
        assertTrue(utilisateurs.findByEmail(email).orElseThrow().getMotDePasseHash().startsWith("pbkdf2-sha256$"));
    }

    @Test void envoiEtModificationMvcAvecValidation() throws Exception {
        Utilisateur autreLocataire = utilisateur(RoleUtilisateur.LOCATAIRE);
        MockHttpSession s = session(autreLocataire);
        mvc.perform(post("/annonces/{id}/candidater", annonce.getId()).session(s).param("_csrf", "jeton-test")
                .param("nombreOccupants", "0").param("usagePrevu", "Habitation")).andExpect(status().isOk());
        mvc.perform(post("/annonces/{id}/candidater", annonce.getId()).session(s).param("_csrf", "jeton-test")
                .param("nombreOccupants", "1").param("usagePrevu", "Habitation")).andExpect(status().is3xxRedirection());
        mvc.perform(post("/propositions/{id}/modifier", proposition.getId()).session(session(locataire)).param("_csrf", "jeton-test")
                .param("loyer", "-1").param("dateDebut", "2027-01-01")).andExpect(status().isOk());
        mvc.perform(post("/propositions/{id}/modifier", proposition.getId()).session(session(locataire)).param("_csrf", "jeton-test")
                .param("loyer", "700000").param("charges", "50000").param("caution", "0").param("avance", "0")
                .param("dateDebut", "2027-01-01")).andExpect(status().is3xxRedirection());
        assertEquals(2, propositionRepository.findFirstByCandidatureIdOrderByNumeroVersionDesc(candidature.getId()).orElseThrow().getNumeroVersion());
    }

    @Test void accordsConcurrentsGenerentUnSeulContrat() throws Exception {
        try (var executor = java.util.concurrent.Executors.newFixedThreadPool(2)) {
            var depart = new java.util.concurrent.CountDownLatch(1);
            var a = executor.submit(() -> { depart.await(); return propositions.accepter(proposition.getId(), bailleur.getId(), RoleUtilisateur.BAILLEUR); });
            var b = executor.submit(() -> { depart.await(); return propositions.accepter(proposition.getId(), locataire.getId(), RoleUtilisateur.LOCATAIRE); });
            depart.countDown();
            a.get(15, java.util.concurrent.TimeUnit.SECONDS); b.get(15, java.util.concurrent.TimeUnit.SECONDS);
        }
        Contrat contrat = contratRepository.findByPropositionId(proposition.getId()).orElseThrow();
        assertEquals("A_SIGNER", contrat.getStatut());
    }
}

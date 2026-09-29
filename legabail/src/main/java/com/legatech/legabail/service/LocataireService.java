package com.legatech.legabail.service;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.form.LocataireForm;
import com.legatech.legabail.repository.UtilisateurRepository;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocataireService {

    private final UtilisateurRepository utilisateurs;
    private final MotDePasseService motsDePasse;

    public LocataireService(UtilisateurRepository utilisateurs,
                            MotDePasseService motsDePasse) {
        this.utilisateurs = utilisateurs;
        this.motsDePasse = motsDePasse;
    }

    public Utilisateur enregistrer(LocataireForm form) {
        MajoriteLocataire.verifier(form.getDateNaissance());
        String email = form.getEmail().strip().toLowerCase(Locale.ROOT);
        if (utilisateurs.existsByEmail(email)) {
            throw new IllegalArgumentException("Cette adresse email est deja utilisee.");
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setDateNaissance(form.getDateNaissance());
        utilisateur.setRole(RoleUtilisateur.LOCATAIRE);
        utilisateur.setNom(form.getNom().strip());
        utilisateur.setPrenom(form.getPrenom().strip());
        utilisateur.setEmail(email);
        utilisateur.setTelephone(form.getTelephone());
        utilisateur.setNumeroPiece(form.getNumeroPiece());
        utilisateur.setMotDePasseHash(motsDePasse.hacher(form.getMotDePasse()));
        return utilisateurs.save(utilisateur);
    }
}

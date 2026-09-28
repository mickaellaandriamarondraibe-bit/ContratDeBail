package com.legatech.legabail.service;

import com.legatech.legabail.entity.*;
import com.legatech.legabail.form.LocataireForm;
import com.legatech.legabail.repository.UtilisateurRepository;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocataireService {
    private final UtilisateurRepository utilisateurs;
    public LocataireService(UtilisateurRepository utilisateurs) { this.utilisateurs = utilisateurs; }

    public Utilisateur enregistrer(LocataireForm form) {
        String email = form.getEmail().strip().toLowerCase(Locale.ROOT);
        if (utilisateurs.existsByEmail(email)) { throw new IllegalArgumentException("Cette adresse email est deja utilisee."); }
        Utilisateur u = new Utilisateur();
        u.setRole(RoleUtilisateur.LOCATAIRE);
        u.setNom(form.getNom().strip()); u.setPrenom(form.getPrenom().strip());
        u.setEmail(email); u.setTelephone(form.getTelephone()); u.setNumeroPiece(form.getNumeroPiece());
        u.setMotDePasseHash(hacher(form.getMotDePasse()));
        return utilisateurs.save(u);
    }

    private String hacher(String motDePasse) {
        byte[] sel = new byte[16];
        new SecureRandom().nextBytes(sel);
        PBEKeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), sel, 600000, 256);
        try {
            byte[] hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
            return "pbkdf2-sha256$600000$" + Base64.getEncoder().encodeToString(sel) + "$" + Base64.getEncoder().encodeToString(hash);
        } catch (java.security.GeneralSecurityException e) {
            throw new IllegalStateException("Impossible de proteger le mot de passe.", e);
        } finally { spec.clearPassword(); }
    }
}

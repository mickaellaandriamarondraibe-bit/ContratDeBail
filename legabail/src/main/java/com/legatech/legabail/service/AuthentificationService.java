package com.legatech.legabail.service;

import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.repository.UtilisateurRepository;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthentificationService {

    private final UtilisateurRepository utilisateurs;
    private final MotDePasseService motsDePasse;

    public AuthentificationService(UtilisateurRepository utilisateurs,
                                   MotDePasseService motsDePasse) {
        this.utilisateurs = utilisateurs;
        this.motsDePasse = motsDePasse;
    }

    @Transactional(readOnly = true)
    public Utilisateur authentifier(String email, String motDePasse) {
        String emailNormalise = email.strip().toLowerCase(Locale.ROOT);
        Utilisateur utilisateur = utilisateurs.findByEmailIgnoreCase(emailNormalise)
                .orElseThrow(this::identifiantsInvalides);

        if (!motsDePasse.verifier(motDePasse, utilisateur.getMotDePasseHash())) {
            throw identifiantsInvalides();
        }
        return utilisateur;
    }

    private IllegalArgumentException identifiantsInvalides() {
        return new IllegalArgumentException("Adresse email ou mot de passe incorrect.");
    }
}

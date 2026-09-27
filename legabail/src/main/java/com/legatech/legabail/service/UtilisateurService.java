package com.legatech.legabail.service;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import com.legatech.legabail.form.BailleurForm;
import com.legatech.legabail.repository.UtilisateurRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur enregistrerBailleur(BailleurForm form) {
        if (utilisateurRepository.existsByEmail(form.getEmail())) {
            throw new IllegalArgumentException("Cette adresse email est déjà utilisée.");
        }

        Utilisateur bailleur = new Utilisateur();
        bailleur.setRole(RoleUtilisateur.BAILLEUR);
        bailleur.setNom(form.getNom());
        bailleur.setPrenom(form.getPrenom());
        bailleur.setEmail(form.getEmail());
        bailleur.setMotDePasseHash(form.getMotDePasse());
        bailleur.setTelephone(form.getTelephone());
        bailleur.setNumeroPiece(form.getNumeroPiece());
        return utilisateurRepository.save(bailleur);
    }

    @Transactional(readOnly = true)
    public Utilisateur trouverParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public Utilisateur trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + email));
    }

    @Transactional(readOnly = true)
    public List<Utilisateur> listerBailleurs() {
        return utilisateurRepository.findByRole(RoleUtilisateur.BAILLEUR);
    }
}

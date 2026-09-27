package com.legatech.legabail.repository;

import com.legatech.legabail.entity.RoleUtilisateur;
import com.legatech.legabail.entity.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    java.util.List<Utilisateur> findByRole(RoleUtilisateur role);
}

package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Signature;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, Long> {
    List<Signature> findByContratIdOrderByDateSignatureAsc(Long id);
    boolean existsByContratIdAndUtilisateurIdAndStatut(Long contratId, Long utilisateurId, String statut);
}

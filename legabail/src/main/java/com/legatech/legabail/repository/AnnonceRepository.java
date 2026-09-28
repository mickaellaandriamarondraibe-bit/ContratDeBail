package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Annonce;
import com.legatech.legabail.entity.StatutAnnonce;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    @Override
    @EntityGraph(attributePaths = {"bien.bailleur"})
    Optional<Annonce> findById(Long id);

    @EntityGraph(attributePaths = {"bien.bailleur"})
    List<Annonce> findByStatut(StatutAnnonce statut);

    @EntityGraph(attributePaths = {"bien.bailleur"})
    List<Annonce> findByBienBailleurId(Long bailleurId);
}

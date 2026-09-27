package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Annonce;
import com.legatech.legabail.entity.StatutAnnonce;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    List<Annonce> findByStatut(StatutAnnonce statut);

    List<Annonce> findByBienBailleurId(Long bailleurId);
}

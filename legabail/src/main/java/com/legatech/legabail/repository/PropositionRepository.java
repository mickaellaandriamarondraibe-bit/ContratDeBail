package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Proposition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    @Query("select p.candidature.id from Proposition p where p.id = :id")
    Optional<Long> trouverCandidatureId(Long id);
    Optional<Proposition> findFirstByCandidatureIdOrderByNumeroVersionDesc(Long id);
    List<Proposition> findByCandidatureIdOrderByNumeroVersionDesc(Long id);
}

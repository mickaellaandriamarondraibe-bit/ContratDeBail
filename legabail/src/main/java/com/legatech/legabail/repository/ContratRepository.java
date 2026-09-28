package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Contrat;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    Optional<Contrat> findByPropositionId(Long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Contrat c where c.id = :id")
    Optional<Contrat> verrouiller(Long id);
}

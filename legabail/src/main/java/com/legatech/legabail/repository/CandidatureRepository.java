package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Candidature;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    @EntityGraph(attributePaths = {"annonce.bien.bailleur", "locataire"})
    List<Candidature> findByLocataireIdOrderByDateCandidatureDesc(Long id);
    @EntityGraph(attributePaths = {"annonce.bien.bailleur", "locataire"})
    List<Candidature> findByAnnonceBienBailleurIdOrderByDateCandidatureDesc(Long id);
    boolean existsByAnnonceIdAndLocataireId(Long annonceId, Long locataireId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Candidature c where c.id = :id")
    Optional<Candidature> verrouiller(Long id);
}

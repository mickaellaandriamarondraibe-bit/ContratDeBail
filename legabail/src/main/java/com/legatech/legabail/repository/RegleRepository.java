package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Regle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegleRepository extends JpaRepository<Regle, Long> {

    Optional<Regle> findByCode(String code);

    List<Regle> findByArticleJuridiqueIdOrderByIdAsc(Long articleId);

    List<Regle> findByBloquanteTrue();

    boolean existsByCode(String code);
}

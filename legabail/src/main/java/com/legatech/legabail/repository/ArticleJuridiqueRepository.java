package com.legatech.legabail.repository;

import com.legatech.legabail.entity.ArticleJuridique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleJuridiqueRepository
        extends JpaRepository<ArticleJuridique, Long> {

    Optional<ArticleJuridique> findByNumero(String numero);

    boolean existsByNumero(String numero);
}

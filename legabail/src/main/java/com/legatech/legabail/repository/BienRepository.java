package com.legatech.legabail.repository;

import com.legatech.legabail.entity.Bien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienRepository extends JpaRepository<Bien, Long> {

    List<Bien> findByBailleurId(Long bailleurId);
}

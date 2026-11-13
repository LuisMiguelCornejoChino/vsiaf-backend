package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    List<Responsable> findByOficinaId(Long oficinaId);
}

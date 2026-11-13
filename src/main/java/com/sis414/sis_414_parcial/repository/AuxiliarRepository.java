package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
    List<Auxiliar> findByGrupoId(Long grupoId);
}

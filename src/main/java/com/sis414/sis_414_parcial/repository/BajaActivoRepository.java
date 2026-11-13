package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.BajaActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BajaActivoRepository extends JpaRepository<BajaActivo, Long> {
}

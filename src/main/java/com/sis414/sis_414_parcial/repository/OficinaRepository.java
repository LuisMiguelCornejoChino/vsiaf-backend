package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
}

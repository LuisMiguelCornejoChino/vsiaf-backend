package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.CuentaPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaPartidaRepository extends JpaRepository<CuentaPartida, Long> {
}

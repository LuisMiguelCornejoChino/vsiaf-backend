package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.UnidadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAdminRepository extends JpaRepository<UnidadAdmin, Long> {
}

package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
}

package com.sis414.sis_414_parcial.repository;

import com.sis414.sis_414_parcial.entity.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgUserRepository extends JpaRepository<OrgUser, Long> {
    Optional<OrgUser> findByTypeAndIdUser(String type, String idUser);
}

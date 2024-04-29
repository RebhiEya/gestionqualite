package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

    List<Audit> findByUserIdUser(Long userId);
}

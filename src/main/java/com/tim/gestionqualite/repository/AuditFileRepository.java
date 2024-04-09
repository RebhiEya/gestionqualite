package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.AuditFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditFileRepository extends JpaRepository<AuditFile, Long> {
}

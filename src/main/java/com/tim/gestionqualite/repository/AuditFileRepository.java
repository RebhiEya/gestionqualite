package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.AuditFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditFileRepository extends JpaRepository<AuditFile, Long> {
}

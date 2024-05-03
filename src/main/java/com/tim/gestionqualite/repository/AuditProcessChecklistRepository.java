package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.AuditProcessChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditProcessChecklistRepository extends JpaRepository<AuditProcessChecklist, Long> {
    AuditProcessChecklist findByAuditIdAuditAndProcessChecklistIdProcessChecklist(Long auditId, Long checklistId);
}

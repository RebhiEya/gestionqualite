package com.tim.gestionqualite.repository;


import com.tim.gestionqualite.entity.AuditProcessChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditProcessChecklistRepository extends JpaRepository<AuditProcessChecklist, Long> {
}

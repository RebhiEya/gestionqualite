package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.ProcessChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessChecklistRepository extends JpaRepository<ProcessChecklist, Long> {
}

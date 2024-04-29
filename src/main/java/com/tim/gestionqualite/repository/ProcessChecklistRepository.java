package com.tim.gestionqualite.repository;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.ProcessChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessChecklistRepository extends JpaRepository<ProcessChecklist, Long> {

    List<ProcessChecklist> findByProcessesIdProcess(Long idProcess);

    List<ProcessChecklist> findByAuditProcessChecklistIdAuditId(Long idAudit);

}

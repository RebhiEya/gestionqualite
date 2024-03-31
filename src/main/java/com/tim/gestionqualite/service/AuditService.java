package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.repository.AuditRepository;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import com.tim.gestionqualite.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;
    @Autowired
    ProcessRepository processRepository;

    @Autowired
    ProcessChecklistRepository processChecklistRepository;
    public Audit selectChecklistOfProcessForAudit(Long auditId, Long processId, Set<Long> checklistIds) {
        Audit audit = auditRepository.findById(auditId).orElseThrow(() -> new IllegalArgumentException("Audit not found"));
        Process process = processRepository.findById(processId).orElseThrow(() -> new IllegalArgumentException("Process not found"));

        if (!audit.getProcesses().contains(process)) {
            audit.getProcesses().add(process);
            auditRepository.save(audit);
        }

        for (Long checklistId : checklistIds) {
            ProcessChecklist checklist = processChecklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("Checklist not found"));
            if (process.getChecklists().contains(checklist)) {
                audit.getChecklists().add(checklist);
            }
        }

        auditRepository.save(audit);
        return audit;
    }
}

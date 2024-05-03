package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.payloads.ChecklistConformityDTO;
import com.tim.gestionqualite.repository.AuditProcessChecklistRepository;
import com.tim.gestionqualite.repository.AuditRepository;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim.gestionqualite.repository.ProcessRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProcessChecklistService {
    @Autowired
    ProcessRepository processRepository;
    @Autowired
    AuditRepository auditRepository;

    @Autowired
    ProcessChecklistRepository processChecklistRepository;
    @Autowired
    AuditProcessChecklistRepository auditProcessChecklistRepository;

    public ProcessChecklist addChecklist(ProcessChecklist checklist) {
       return processChecklistRepository.save(checklist);
    }

    public List<ProcessChecklist> retrieveAllProcessChecklists() {
        return processChecklistRepository.findAll();
    }


    public List<ProcessChecklist> deleteProcessChecklist(Long processChecklistId) {
            Optional<ProcessChecklist> optionalProcessChecklist = processChecklistRepository.findById(processChecklistId);
            if (optionalProcessChecklist.isPresent()) {
                ProcessChecklist processChecklist = optionalProcessChecklist.get();
                processChecklist.getProcesses().forEach(process -> process.getProcessChecklist().remove(processChecklist));
                processChecklistRepository.delete(processChecklist);
               return processChecklistRepository.findAll();
            } else {
                throw new NoSuchElementException("Process Checklist not found with id: " + processChecklistId);
            }
    }
    public List<ProcessChecklist> retrieveByProcess(Long idProcess) {
        processRepository.findById(idProcess).orElseThrow(() -> new IllegalArgumentException("Process not found"));

        return processChecklistRepository.findByProcessesIdProcess(idProcess);
    }
    public List<ProcessChecklist> retrieveByAudit(Long idAudit) {
        auditRepository.findById(idAudit).orElseThrow(() -> new IllegalArgumentException("Audit not found"));

        return processChecklistRepository.findByAuditProcessChecklistIdAuditId(idAudit);
    }

    public void updateConformityForChecklist(Long auditId, Long checklistId, boolean newConformityValue) {
        AuditProcessChecklist auditProcessChecklist = auditProcessChecklistRepository.findByAuditIdAuditAndProcessChecklistIdProcessChecklist(auditId, checklistId);

        if (auditProcessChecklist != null) {
            auditProcessChecklist.setConformity(newConformityValue);
            auditProcessChecklistRepository.save(auditProcessChecklist);
        } else {
            throw new IllegalArgumentException("association not found");
        }
    }
    public List<ChecklistConformityDTO> getChecklistsAndConformityByAuditId(Long controlId) {
        List<AuditProcessChecklist> auditProcessChecklists = auditProcessChecklistRepository.findByAuditIdAudit(controlId);
        List<ChecklistConformityDTO> checklistConformityDTOList = new ArrayList<>();

        for (AuditProcessChecklist auditProcessChecklist : auditProcessChecklists) {
            ProcessChecklist processChecklist = processChecklistRepository.findById(auditProcessChecklist.getId().getChecklistId()).orElse(null);
            if (processChecklist != null) {
                ChecklistConformityDTO checklistConformityDTO = new ChecklistConformityDTO(processChecklist, auditProcessChecklist.getConformity());
                checklistConformityDTOList.add(checklistConformityDTO);
            }
        }

        return checklistConformityDTOList;
    }
}




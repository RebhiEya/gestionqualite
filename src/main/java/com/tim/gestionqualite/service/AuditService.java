package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.payloads.AuditResponse;
import com.tim.gestionqualite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;
    @Autowired
    ProcessRepository processRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    AuditFileRepository auditFileRepository;

    @Autowired
    ProcessChecklistRepository processChecklistRepository;

    @Autowired
    AuditProcessChecklistRepository auditProcessChecklistRepository;

    public List<Audit> retrieveAllAudits() {
        return auditRepository.findAll();
    }

    public Optional<Audit> retrieveAuditById(Long auditId) {
        return auditRepository.findById(auditId);
    }

    @Transactional
    public AuditResponse addAudit(Audit audit, Long processId, Set<Long> checklistIds) {
        AuditResponse auditResponse = new AuditResponse();
        // Step 1: Find the Process by processId
        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new IllegalArgumentException("Process with ID " + processId + " not found"));
        auditResponse.setProcess(process);
        // Step 3: Create a Set<AuditProcessChecklist> for each ProcessChecklist
        Set<AuditProcessChecklist> auditProcessChecklists = new HashSet<>();
        Set<ProcessChecklist> selectedChecklists = new HashSet<>();
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {

                ProcessChecklist checklist = processChecklistRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                selectedChecklists.add(checklist);

                AuditProcessChecklist auditProcessChecklist = new AuditProcessChecklist();
                AuditProcessChecklistId auditProcessChecklistId = new AuditProcessChecklistId();

                auditProcessChecklistId.setAuditId(null);
                auditProcessChecklistId.setProcessId(process.getIdProcess());
                auditProcessChecklistId.setChecklistId(checklistId);

                auditProcessChecklist.setId(auditProcessChecklistId);
                auditProcessChecklist.setAudit(audit);
                auditProcessChecklist.setProcess(process);
                auditProcessChecklist.setChecklist(checklist);

                auditProcessChecklists.add(auditProcessChecklist);
            }
        }
        // Step 4: Set the AuditProcessChecklist in the Audit
        audit.setProcessChecklist(auditProcessChecklists);

        // Step 5: Save the Audit with ProcessChecklist
        auditRepository.save(audit);

        auditResponse.setAudit(audit);
        auditResponse.setChecklist(selectedChecklists);

        return auditResponse;
    }

    @Transactional
    public AuditResponse updateAudit(Long auditId, Audit updatedAudit, Long processId, Set<Long> checklistIds) {
        // Step 1: Find the existing Audit by auditId
        Audit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new IllegalArgumentException("Audit with ID " + auditId + " not found"));

        // Step 2: Update the existing Audit with new details
        audit.setCategory(updatedAudit.getCategory());
        audit.setDesignation(updatedAudit.getDesignation());
        audit.setEndDate(updatedAudit.getEndDate());
        audit.setStartDate(updatedAudit.getStartDate());
        audit.setReference(updatedAudit.getReference());
        audit.setState(updatedAudit.getState());
        // Update other fields as needed

        // Step 3: Find the Process by processId
        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new IllegalArgumentException("Process with ID " + processId + " not found"));

        // Step 4: Retrieve the existing ProcessChecklists associated with the Audit
        Set<AuditProcessChecklist> existingAuditProcessChecklists = audit.getProcessChecklist();

        // Step 5: Create a Set<AuditProcessChecklist> for each selected ProcessChecklist
        Set<AuditProcessChecklist> auditProcessChecklists = new HashSet<>();
        Set<ProcessChecklist> selectedChecklists = new HashSet<>();
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {
                ProcessChecklist checklist = processChecklistRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                selectedChecklists.add(checklist);

                // Check if the selected checklist is already associated with the Audit
                boolean existsInAudit = existingAuditProcessChecklists.stream()
                        .anyMatch(a -> a.getChecklist().getIdProcessChecklist().equals(checklistId));

                // If not already associated, create a new AuditProcessChecklist
                if (!existsInAudit) {
                    AuditProcessChecklist auditProcessChecklist = new AuditProcessChecklist();
                    AuditProcessChecklistId auditProcessChecklistId = new AuditProcessChecklistId();

                    auditProcessChecklistId.setAuditId(auditId); // Use the provided auditId
                    auditProcessChecklistId.setProcessId(process.getIdProcess());
                    auditProcessChecklistId.setChecklistId(checklistId);

                    auditProcessChecklist.setId(auditProcessChecklistId);
                    auditProcessChecklist.setAudit(audit);
                    auditProcessChecklist.setProcess(process);
                    auditProcessChecklist.setChecklist(checklist);

                    auditProcessChecklists.add(auditProcessChecklist);
                }
            }
        }

        // Step 6: Set the updated AuditProcessChecklists in the Audit
        audit.getProcessChecklist().clear(); // Clear existing associations
        audit.getProcessChecklist().addAll(auditProcessChecklists);

        // Step 7: Save the updated Audit with ProcessChecklists
        auditRepository.save(audit);

        // Step 8: Set the response fields
        AuditResponse auditResponse = new AuditResponse();
        auditResponse.setAudit(audit);
        auditResponse.setProcess(process);
        auditResponse.setChecklist(selectedChecklists);

        return auditResponse;
    }

    public void deleteAudit(Long auditId) {
        // Vérifiez d'abord si l'audit existe
        if (!auditRepository.existsById(auditId)) {
            throw new IllegalArgumentException("Audit not found");
        }

        // Supprimez l'audit de la base de données
        auditRepository.deleteById(auditId);
    }


    public void addFileToAudit(Long auditId, AuditFile auditFile) {
        // Récupérer l'audit associé à l'ID
        Audit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new IllegalArgumentException("Audit non trouvé avec ID: " + auditId));

        // Associer le fichier d'audit à l'audit
        audit.getAuditFiles().add(auditFile);
        auditFile.setAudit(audit);

        // Enregistrer le fichier d'audit dans la base de données
        auditFileRepository.save(auditFile);
    }

    public List<AuditFile> getAllFilesByAuditID(Long auditId) {
        // Récupérer l'audit associé à l'ID
        Audit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new IllegalArgumentException("Audit non trouvé avec ID: " + auditId));

        // Récupérer tous les fichiers associés à cet audit
        return audit.getAuditFiles();
    }
}

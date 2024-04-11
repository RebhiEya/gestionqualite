package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;
    @Autowired
    ProcessRepository processRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserTeamRoleRepository userTeamRoleRepository;

    @Autowired
    AuditFileRepository auditFileRepository;

    @Autowired
    ProcessChecklistRepository processChecklistRepository;

    @Autowired
    AuditProcessChecklistRepository auditProcessChecklistRepository;
    public List<Audit> retrieveAllAudits() {
        return  auditRepository.findAll();
    }
    public Optional<Audit> retrieveAuditById(Long auditId) {
        return  auditRepository.findById(auditId);
    }
    @Transactional
    public Audit addAudit(Audit audit, Long processId,Set<Long> checklistIds) {
        // Step 1: Find the Process by processId
        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new IllegalArgumentException("Process with ID " + processId + " not found"));

        // Step 2: Retrieve the ProcessChecklist associated with the Process
        Set<ProcessChecklist> processChecklists = process.getProcessChecklist();

        // Step 3: Create a Set<AuditProcessChecklist> for each ProcessChecklist
        Set<AuditProcessChecklist> auditProcessChecklists = new HashSet<>();
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {

                ProcessChecklist checklist = processChecklistRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                AuditProcessChecklist auditProcessChecklist = new AuditProcessChecklist();
                AuditProcessChecklistId auditProcessChecklistId = new AuditProcessChecklistId();

                auditProcessChecklistId.setAuditId(null); // Will be set when saved to DB
                auditProcessChecklistId.setProcessId(process.getIdProcess());
                auditProcessChecklistId.setChecklistId(checklistId);

                auditProcessChecklist.setId(auditProcessChecklistId);
                auditProcessChecklist.setAudit(audit);  // Use the audit being added
                auditProcessChecklist.setProcess(process);
                auditProcessChecklist.setChecklist(checklist);

                auditProcessChecklists.add(auditProcessChecklist);
            }
        }
        // Step 4: Set the AuditProcessChecklist in the Audit
        audit.setProcessChecklist(auditProcessChecklists);

        // Step 5: Save the Audit with ProcessChecklist
        return auditRepository.save(audit);
    }

    public Audit updateAudit(Long auditId, Audit updatedAudit, Long processId, Set<Long> checklistIds) {
        Audit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new IllegalArgumentException("Audit not found"));

        // Mise à jour des attributs de l'audit avec les nouvelles valeurs
        audit.setCategory(updatedAudit.getCategory());
        audit.setDesignation(updatedAudit.getDesignation());
        audit.setStartDate(updatedAudit.getStartDate());
        audit.setEndDate(updatedAudit.getEndDate());
        audit.setState(updatedAudit.getState());
        audit.setReference(updatedAudit.getReference());

        // Mise à jour des processus associés à l'audit
        if (processId != null) {
            Process process = processRepository.findById(processId)
                    .orElseThrow(() -> new IllegalArgumentException("Process not found"));

            // Remove existing associations and create a new one
            audit.getProcessChecklist().clear(); // Clear existing associations

            // Create a new AuditProcessChecklist for the Audit and Process
            AuditProcessChecklist auditProcessChecklist = new AuditProcessChecklist();
            auditProcessChecklist.setAudit(audit);
            auditProcessChecklist.setProcess(process);

            // Save the new AuditProcessChecklist
            auditProcessChecklistRepository.save(auditProcessChecklist);
        }

        // Mise à jour des listes de contrôle associées à l'audit
        if (checklistIds != null) {
            audit.getProcessChecklist().clear(); // Clear existing associations

            for (Long checklistId : checklistIds) {
                ProcessChecklist checklist = processChecklistRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                // Create a new AuditProcessChecklist for each selected checklist
                AuditProcessChecklist checklistAuditProcessChecklist = new AuditProcessChecklist();
                checklistAuditProcessChecklist.setAudit(audit);
                // Since there's no direct association with Process, we can omit setting Process
                checklistAuditProcessChecklist.setChecklist(checklist);

                // Save the new AuditProcessChecklist
                auditProcessChecklistRepository.save(checklistAuditProcessChecklist);
            }
        }

        // Enregistrement de l'audit mis à jour dans la base de données
        return auditRepository.save(audit);
    }

    public void deleteAudit(Long auditId) {
        // Vérifiez d'abord si l'audit existe
        if (!auditRepository.existsById(auditId)) {
            throw new IllegalArgumentException("Audit not found");
        }

        // Supprimez l'audit de la base de données
        auditRepository.deleteById(auditId);
    }

    public void assignRolesToUsersInTeamAndAddTeamToAudit(List<Long> userIds, boolean auditeur, boolean controller) {

        Team team = new Team();

        team = teamRepository.save(team);
        Audit audit = new Audit();
        audit = auditRepository.save(audit);
        audit.getTeams().add(team);

        // Assign roles to users in the team
        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Create or update UserTeamRole
            UserTeamRole userTeamRole = userTeamRoleRepository.findByUserAndTeam(user, team)
                    .orElse(new UserTeamRole(user, team));

            userTeamRole.setAuditeur(auditeur);
            userTeamRole.setController(controller);

            userTeamRoleRepository.save(userTeamRole);
        }
    }
    public void updateTeamAudit(Long teamId, Long auditId, List<Long> userIds, boolean auditeur, boolean controller) {
        // Récupérer l'équipe et l'audit de la base de données
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Équipe non trouvée"));

        Audit audit = auditRepository.findById(auditId)
                .orElseThrow(() -> new IllegalArgumentException("Audit non trouvé"));

        // Mettre à jour les rôles des utilisateurs dans l'équipe
        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

            UserTeamRole userTeamRole = userTeamRoleRepository.findByUserAndTeam(user, team)
                    .orElseThrow(() -> new IllegalArgumentException("Rôle utilisateur dans l'équipe non trouvé"));

            userTeamRole.setAuditeur(auditeur);
            userTeamRole.setController(controller);
            userTeamRoleRepository.save(userTeamRole);
        }

        // Ajouter l'équipe à l'audit si elle n'est pas déjà associée
        if (!audit.getTeams().contains(team)) {
            audit.getTeams().add(team);
        }

        // Enregistrer l'audit mis à jour
        auditRepository.save(audit);
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

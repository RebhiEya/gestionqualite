package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.entity.AuditFile;
import com.tim.gestionqualite.payloads.AssignRolesAndAddToAuditRequest;
import com.tim.gestionqualite.payloads.AuditProcessRequest;
import com.tim.gestionqualite.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/audit")
public class AuditController {
    @Autowired
    AuditService auditService;

    @GetMapping("getAll")
    public ResponseEntity<List<Audit>> getAllAudit() {
        List<Audit> ListAudit = auditService.retrieveAllAudits();
        return ResponseEntity.ok(ListAudit);
    }
    @GetMapping("get/{auditId}")
    public ResponseEntity<Optional<Audit>> getAuditById(@PathVariable Long auditId) {
        Optional<Audit> audit = auditService.retrieveAuditById(auditId);
        return ResponseEntity.ok(audit);
    }
    @PostMapping("add/select-checklist-for-audit/{processId}")
    public ResponseEntity<Audit> addAudit(@RequestBody AuditProcessRequest request) {
        Audit updatedAudit = auditService.addAudit(request.getAudit() , request.getProcessId(), request.getChecklistIds());
        return ResponseEntity.ok(updatedAudit);
    }
    @DeleteMapping("delete/{auditId}")
    public ResponseEntity<String> deleteAudit(@PathVariable Long auditId) {
        auditService.deleteAudit(auditId);
        return ResponseEntity.ok("Audit deleted successfully");
    }

    @PutMapping("update/{auditId}")
    public ResponseEntity<Audit> updateAudit(@PathVariable Long auditId, @RequestBody AuditProcessRequest request) {
        Audit audit = auditService.updateAudit(auditId, request.getAudit(), request.getProcessId(), request.getChecklistIds());
        return ResponseEntity.ok(audit);
    }
    @PostMapping("/assign-roles-and-add-to-audit")
    public ResponseEntity<String> assignRolesAndAddToAudit(@RequestBody AssignRolesAndAddToAuditRequest request) {
        auditService.assignRolesToUsersInTeamAndAddTeamToAudit(
                request.getUserIds(),
                request.getController(),
                request.getAuditeur()
        );
        return ResponseEntity.ok("Roles assigned and team added to audit successfully");
    }
    @PutMapping("/updateTeam")
    public ResponseEntity<String> updateTeamAudit(@RequestParam Long teamId,
                                                  @RequestParam Long auditId,
                                                  @RequestBody AssignRolesAndAddToAuditRequest request) {
        auditService.updateTeamAudit(teamId, auditId, request.getUserIds(), request.getAuditeur(), request.getController());
        return ResponseEntity.ok("L'équipe a été mise à jour dans l'audit avec succès.");
    }

    @PostMapping("/{auditId}/files")
    public ResponseEntity<String> addFileToAudit(@PathVariable Long auditId, @RequestBody AuditFile auditFile) {
        auditService.addFileToAudit(auditId, auditFile);
        return ResponseEntity.ok("Fichier ajouté avec succès à l'audit avec ID: " + auditId);
    }

    @GetMapping("/{auditId}/files")
    public ResponseEntity<List<AuditFile>> getAllFilesByAuditID(@PathVariable Long auditId) {
        List<AuditFile> files = auditService.getAllFilesByAuditID(auditId);
        return ResponseEntity.ok(files);
    }
}

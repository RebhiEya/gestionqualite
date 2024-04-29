package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.entity.AuditFile;
import com.tim.gestionqualite.payloads.AuditProcessRequest;
import com.tim.gestionqualite.payloads.AuditResponse;
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

    @PostMapping("add")
    public ResponseEntity<AuditResponse> addAudit(@RequestBody AuditProcessRequest request) {
        AuditResponse updatedAudit = auditService.addAudit(request.getAudit(), request.getProcessId(), request.getChecklistIds());
        return ResponseEntity.ok(updatedAudit);
    }

    @DeleteMapping("delete/{auditId}")
    public ResponseEntity<String> deleteAudit(@PathVariable Long auditId) {
        auditService.deleteAudit(auditId);
        return ResponseEntity.ok("Audit deleted successfully");
    }

    @PutMapping("update/{auditId}")
    public ResponseEntity<AuditResponse> updateAudit(@PathVariable Long auditId, @RequestBody AuditProcessRequest request) {
        AuditResponse audit = auditService.updateAudit(auditId, request.getAudit(), request.getProcessId(), request.getChecklistIds());
        return ResponseEntity.ok(audit);
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

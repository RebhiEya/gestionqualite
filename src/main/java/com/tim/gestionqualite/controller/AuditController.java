package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/audit")
public class AuditController {
    @Autowired
    AuditService auditService;
    @PostMapping("/select-checklist-for-audit/{auditId}/{processId}")
    public ResponseEntity<Audit> selectChecklistOfProcessForAudit(@PathVariable Long auditId, @PathVariable Long processId, @RequestBody Set<Long> checklistIds) {
        Audit updatedAudit = auditService.selectChecklistOfProcessForAudit(auditId, processId, checklistIds);
        return ResponseEntity.ok(updatedAudit);
    }
}

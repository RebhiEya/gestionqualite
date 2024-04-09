package com.tim.gestionqualite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AuditProcessChecklistId implements Serializable {

    @Column(name = "audit_id")
    private Long auditId;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "checklist_id")
    private Long checklistId;

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }
}


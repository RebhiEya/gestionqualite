package com.tim.gestionqualite.entity;




import jakarta.persistence.*;

@Entity
@Table(name = "audit_process_checklist")
public class AuditProcessChecklist {

    @EmbeddedId
    private AuditProcessChecklistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("auditId")
    private Audit audit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("processId")
    private Process process;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("checklistId")
    private ProcessChecklist processChecklist;

    public AuditProcessChecklistId getId() {
        return id;
    }

    public void setId(AuditProcessChecklistId id) {
        this.id = id;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ProcessChecklist getChecklist() {
        return processChecklist;
    }

    public void setChecklist(ProcessChecklist checklist) {
        this.processChecklist = checklist;
    }
}


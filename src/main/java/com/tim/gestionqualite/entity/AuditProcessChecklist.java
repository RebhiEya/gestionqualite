package com.tim.gestionqualite.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_process_checklist")
public class AuditProcessChecklist {


    @JsonIgnore
    @EmbeddedId
    private AuditProcessChecklistId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("auditId")
    private Audit audit;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("processId")
    private Process process;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("checklistId")
    private ProcessChecklist processChecklist;

    @Column(nullable = true)
    private Boolean conformity;
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

    public ProcessChecklist getProcessChecklist() {
        return processChecklist;
    }

    public void setProcessChecklist(ProcessChecklist processChecklist) {
        this.processChecklist = processChecklist;
    }

    public Boolean getConformity() {
        return conformity;
    }

    public void setConformity(Boolean conformity) {
        this.conformity = conformity;
    }
}


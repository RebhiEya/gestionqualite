package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.Audit;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class AuditProcessRequest {

    private Audit audit ;

    private  Long processId ;

    private Set<Long> checklistIds ;

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Set<Long> getChecklistIds() {
        return checklistIds;
    }

    public void setChecklistIds(Set<Long> checklistIds) {
        this.checklistIds = checklistIds;
    }
}

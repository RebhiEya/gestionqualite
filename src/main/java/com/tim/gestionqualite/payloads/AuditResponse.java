package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.Audit;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Set;



public class AuditResponse {
    private Audit audit ;

    private Process process ;

    Set<ProcessChecklist> checklist ;

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

    public Set<ProcessChecklist> getChecklist() {
        return checklist;
    }

    public void setChecklist(Set<ProcessChecklist> checklist) {
        this.checklist = checklist;
    }
}

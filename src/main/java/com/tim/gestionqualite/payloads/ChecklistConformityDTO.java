package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;

import java.util.Optional;

public class ChecklistConformityDTO {

    private ControlChecklist checklist ;

    private ProcessChecklist checklistprocess ;
    private Boolean conformity ;

    public ChecklistConformityDTO(ProcessChecklist checklistprocess, Boolean conformity) {
        this.checklistprocess = checklistprocess;
        this.conformity = conformity;
    }

    public ChecklistConformityDTO(ControlChecklist checklist, Boolean conformity) {
        this.checklist = checklist;
        this.conformity = conformity;
    }

    public ControlChecklist getChecklist() {
        return checklist;
    }

    public void setChecklist(ControlChecklist checklist) {
        this.checklist = checklist;
    }

    public Boolean getConformity() {
        return conformity;
    }

    public void setConformity(Boolean conformity) {
        this.conformity = conformity;
    }
}

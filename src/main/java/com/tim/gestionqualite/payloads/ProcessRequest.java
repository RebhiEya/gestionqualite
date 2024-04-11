package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.Process;

import java.util.List;

public class ProcessRequest {
    private Process process;
    private List<Long> checklistIds;

    // Getters and setters
    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public List<Long> getChecklistIds() {
        return checklistIds;
    }

    public void setChecklistIds(List<Long> checklistIds) {
        this.checklistIds = checklistIds;
    }
}


package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import com.tim.gestionqualite.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    ProcessChecklistRepository processCheklistRepository;

    public Process addProcessWithoutChecklist(Process process) {
        processRepository.save(process);
        return process;
    }
    public Process addProcessWithChecklist(Process process, Long checklistId) {
        Process createdProcess = processRepository.save(process);
        if (checklistId != null) {
            ProcessChecklist checklist = processCheklistRepository.findById(checklistId)
                    .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));
            createdProcess.getChecklists().add(checklist);
            processRepository.save(createdProcess);
        }
        return createdProcess;
    }
    public Process assignChecklistToProcess(Long processId, Long checklistId) {
        Process process = processRepository.findById(processId).orElseThrow(() -> new IllegalArgumentException("Process not found"));
        ProcessChecklist checklist = processCheklistRepository.findById(checklistId).orElseThrow(() -> new IllegalArgumentException("Checklist not found"));
        process.getChecklists().add(checklist);
        processRepository.save(process);
        return process;
    }
}

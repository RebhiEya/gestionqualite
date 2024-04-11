package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import com.tim.gestionqualite.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Process addProcessWithChecklists(Process process, List<Long> checklistIds) {
        Process createdProcess = processRepository.save(process);
        if (checklistIds != null && !checklistIds.isEmpty()) {
            List<ProcessChecklist> checklists = processCheklistRepository.findAllById(checklistIds);
            if (checklists.isEmpty()) {
                throw new IllegalArgumentException("Checklists not found");
            }
            createdProcess.getProcessChecklist().addAll(checklists);
            processRepository.save(createdProcess);
        }
        return createdProcess;
    }
    public Process addAndAssignChecklistToProcess(Long processId , ProcessChecklist processChecklist) {
        processChecklist = processCheklistRepository.save(processChecklist);
        Process process = processRepository.findById(processId).orElseThrow(() -> new IllegalArgumentException("Process not found"));
        process.getProcessChecklist().add(processChecklist);
        processRepository.save(process);
        return process;
    }
}

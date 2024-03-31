package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessChecklistService {


    @Autowired
    ProcessChecklistRepository processChecklistRepository;

    public ProcessChecklist addChecklist(ProcessChecklist checklist) {
       return processChecklistRepository.save(checklist);
    }
}

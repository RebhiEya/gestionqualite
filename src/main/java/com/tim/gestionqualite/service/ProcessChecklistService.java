package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.repository.AuditRepository;
import com.tim.gestionqualite.repository.ProcessChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim.gestionqualite.repository.ProcessRepository;
import java.util.List;

@Service
public class ProcessChecklistService {
    @Autowired
    ProcessRepository processRepository;
    @Autowired
    AuditRepository auditRepository;

    @Autowired
    ProcessChecklistRepository processChecklistRepository;

    public ProcessChecklist addChecklist(ProcessChecklist checklist) {
       return processChecklistRepository.save(checklist);
    }

    public List<ProcessChecklist> retrieveAllProcessChecklists() {
        return processChecklistRepository.findAll();
    }


    public List<ProcessChecklist> deleteProcessChecklist(Long idProcessChecklist) {
        // Vérifiez d'abord si le produit existe
        if (!processChecklistRepository.existsById(idProcessChecklist)) {
            throw new IllegalArgumentException("ProcessChecklist not found");
        }
        // Supprimez le produit de la base de données
        processChecklistRepository.deleteById(idProcessChecklist);
        // renvoyer la liste des produits
        return processChecklistRepository.findAll();
    }
    public List<ProcessChecklist> retrieveByProcess(Long idProcess) {
        processRepository.findById(idProcess).orElseThrow(() -> new IllegalArgumentException("Process not found"));

        return processChecklistRepository.findByProcessesIdProcess(idProcess);
    }
    public List<ProcessChecklist> retrieveByAudit(Long idAudit) {
        auditRepository.findById(idAudit).orElseThrow(() -> new IllegalArgumentException("Audit not found"));

        return processChecklistRepository.findByAuditProcessChecklistIdAuditId(idAudit);
    }



}




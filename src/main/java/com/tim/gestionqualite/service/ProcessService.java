package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.entity.Produit;
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


    public List<Process> retrieveAllProcesses() {
        return processRepository.findAll();
    }
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
    public Process addChecklistAndAssignToProcess(Long processId , ProcessChecklist processChecklist) {
        processChecklist = processCheklistRepository.save(processChecklist);
        Process process = processRepository.findById(processId).orElseThrow(() -> new IllegalArgumentException("Process not found"));
        process.getProcessChecklist().add(processChecklist);
        processRepository.save(process);
        return process;
    }

    public List<Process> deleteProsses(Long prossesId) {
        // Vérifiez d'abord si le produit existe
        if (!processRepository.existsById(prossesId)) {
            throw new IllegalArgumentException("Produit not found");
        }

        // Supprimez le produit de la base de données
        processRepository.deleteById(prossesId);
        // renvoyer la liste des produits
        return processRepository.findAll();
    }


    public Process updateProcess(Long idProcess, Process updatedProcess) {
        Process existingProcess = processRepository.findById(idProcess)
                .orElseThrow(() -> new RuntimeException("Process non trouvé avec l'ID : " + idProcess));

        // Mettre à jour les champs que vous souhaitez
        existingProcess.setNom(updatedProcess.getNom());
        existingProcess.setRecommendation(updatedProcess.getRecommendation());
        existingProcess.setWeakness(updatedProcess.getWeakness());
        existingProcess.setStrength(updatedProcess.getStrength());

        return processRepository.save(existingProcess);
    }
}

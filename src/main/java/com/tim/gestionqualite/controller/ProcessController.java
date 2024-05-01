package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.payloads.ProcessRequest;
import com.tim.gestionqualite.repository.ProcessRepository;
import com.tim.gestionqualite.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;
    ProcessRepository processRepository;


    @GetMapping("/getAll")
    public ResponseEntity<List<Process>> getAllProcesses() {
        List<Process> listProcesses = processService.retrieveAllProcesses();
        return ResponseEntity.ok(listProcesses);
    }


    @PostMapping("/add")
    public ResponseEntity<Process> addProcess(@RequestBody Process process) {
        Process createdProcess = processService.addProcessWithoutChecklist(process);
        return ResponseEntity.ok(createdProcess);
    }

    @DeleteMapping("delete/{prossesId}")
    public ResponseEntity<List<Process>> deleteProsess(@PathVariable Long prossesId) {
        List<Process> ListProsess =  processService.deleteProsses(prossesId);
        return ResponseEntity.ok(ListProsess);
    }
    @PostMapping("/assign-checklist-to-process/{processId}")
    public ResponseEntity<Process> assignChecklistToProcess(@PathVariable Long processId, @RequestBody ProcessChecklist processChecklist) {
        Process createdProcess = processService.addChecklistAndAssignToProcess(processId, processChecklist);
        return ResponseEntity.ok(createdProcess);
    }

    @PostMapping("/process-with-checklists")
    public ResponseEntity<Process> addProcessAndAssignChecklists(@RequestBody ProcessRequest request) {
        Process createdProcess = processService.addProcessWithChecklists(request.getProcess(), request.getChecklistIds());
        return ResponseEntity.ok(createdProcess);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Process> updateProcess(@PathVariable Long id, @RequestBody Process updatedProcess) {
        Process updated = processService.updateProcess(id, updatedProcess);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/getByProcess/{processId}")
    public ResponseEntity<List<ProcessChecklist>> getchecklist(@PathVariable Long processId) {
        List<ProcessChecklist> CheckList =  processService.getchecklist(processId);
        return ResponseEntity.ok(CheckList);
    }
    @GetMapping("get/{prossesId}")
    public ResponseEntity<Optional<Process>> getProcessById(@PathVariable Long prossesId) {
        Optional<Process> process = processService.retrieveProcessById(prossesId);
        return ResponseEntity.ok(process);
    }

}

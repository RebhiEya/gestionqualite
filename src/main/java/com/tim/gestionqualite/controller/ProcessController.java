package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;


    @PostMapping("/process")
    public ResponseEntity<Process> addProcess(@RequestBody Process process) {
        Process createdProcess = processService.addProcessWithoutChecklist(process);
        return ResponseEntity.ok(createdProcess);
    }
    @PostMapping("/assign-checklist-to-process/{processId}/{checklistId}")
    public ResponseEntity<Process> assignChecklistToProcess(@PathVariable Long processId, @RequestBody ProcessChecklist processChecklist) {
        Process createdProcess = processService.addAndAssignChecklistToProcess(processId, processChecklist);
        return ResponseEntity.ok(createdProcess);
    }

    @PostMapping("/process-with-checklist")
    public ResponseEntity<Process> addProcessAndAssignChecklist(@RequestBody Process process, @RequestParam(required = false) Long checklistId) {
        Process createdProcess = processService.addProcessWithChecklist(process, checklistId);
        return ResponseEntity.ok(createdProcess);
    }
}

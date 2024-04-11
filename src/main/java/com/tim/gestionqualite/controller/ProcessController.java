package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.payloads.ProcessRequest;
import com.tim.gestionqualite.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;


    @PostMapping("/add")
    public ResponseEntity<Process> addProcess(@RequestBody Process process) {
        Process createdProcess = processService.addProcessWithoutChecklist(process);
        return ResponseEntity.ok(createdProcess);
    }
    @PostMapping("/assign-checklist-to-process/{processId}")
    public ResponseEntity<Process> assignChecklistToProcess(@PathVariable Long processId, @RequestBody ProcessChecklist processChecklist) {
        Process createdProcess = processService.addAndAssignChecklistToProcess(processId, processChecklist);
        return ResponseEntity.ok(createdProcess);
    }

    @PostMapping("/process-with-checklists")
    public ResponseEntity<Process> addProcessAndAssignChecklists(@RequestBody ProcessRequest request) {
        Process createdProcess = processService.addProcessWithChecklists(request.getProcess(), request.getChecklistIds());
        return ResponseEntity.ok(createdProcess);
    }
}

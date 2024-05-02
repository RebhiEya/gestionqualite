package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.service.ProcessChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tim.gestionqualite.service.ProcessService;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/processCheklist")
public class ProcessChecklistController {

    @Autowired
    ProcessChecklistService processChecklistService;
    @Autowired
    ProcessService processService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProcessChecklist>> getAllProcessChecklist() {
        List<ProcessChecklist> listProcesses = processChecklistService.retrieveAllProcessChecklists();
        return ResponseEntity.ok(listProcesses);
    }

    @DeleteMapping("delete/{idProcessChecklist}")
    public ResponseEntity<List<ProcessChecklist>> deleteProcesschecklist(@PathVariable Long idProcessChecklist) {
        List<ProcessChecklist> listChecklistProcesses =  processChecklistService.deleteProcessChecklist(idProcessChecklist);
        return ResponseEntity.ok(listChecklistProcesses);
    }

    @GetMapping("getByIdProcess/{idProcess}")
    public ResponseEntity<List<ProcessChecklist>> getChecklistByProcess(@PathVariable Long idProcess){
        List<ProcessChecklist> checklists = processChecklistService.retrieveByProcess(idProcess);
        return ResponseEntity.ok(checklists);
    }

    @GetMapping("getByIdAudit/{idAudit}")
    public ResponseEntity<List<ProcessChecklist>> getChecklistByAudit(@PathVariable Long idAudit){
        List<ProcessChecklist> checklists = processChecklistService.retrieveByAudit(idAudit);
        return ResponseEntity.ok(checklists);
    }

    @PutMapping("/updateConformity")
    public ResponseEntity<String> updateConformity(@RequestParam Long auditId,
                                                   @RequestParam Long checklistId,
                                                   @RequestParam boolean conformity) {
        processChecklistService.updateConformityForChecklist(auditId, checklistId, conformity);
        return ResponseEntity.ok("Conformity updated successfully");
    }

}

package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.service.ProcessChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/processCheklist")
public class ProcessChecklistController {

    @Autowired
    ProcessChecklistService processChecklistService;

  //pas d'ajout chechklist sans process
   /* @PostMapping("/add")
    public ResponseEntity<ProcessChecklist> addChecklist(@RequestBody ProcessChecklist checklist) {
     ProcessChecklist createdChecklist =   processChecklistService.addChecklist(checklist);
        return ResponseEntity.ok(createdChecklist);
    }*/
}

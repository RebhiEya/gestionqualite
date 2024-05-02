package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.payloads.ChecklistConformityDTO;
import com.tim.gestionqualite.service.ControlCheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/controlCheckList")
public class ControlCheckListController {

    @Autowired
    ControlCheckListService controlCheckListService;

    @GetMapping("getByIdProduit/{idProduit}")
    public ResponseEntity<List<ControlChecklist>> getChecklistByProduit(@PathVariable Long idProduit) {
        List<ControlChecklist> checklists = controlCheckListService.retrieveByProduit(idProduit);
        return ResponseEntity.ok(checklists);
    }

    @GetMapping("getByIdControl/{idControl}")
    public ResponseEntity<List<ChecklistConformityDTO>> getChecklistByControl(@PathVariable Long idControl) {
        List<ChecklistConformityDTO> checklists = controlCheckListService.getChecklistsAndConformityByControlId(idControl);
        return ResponseEntity.ok(checklists);
    }

    @GetMapping("/updateConformity")
    public ResponseEntity<String> updateConformity(@RequestParam Long controlId,
                                                   @RequestParam Long checklistId,
                                                   @RequestParam boolean conformity) {
        controlCheckListService.updateConformityForChecklist(controlId, checklistId, conformity);
        return ResponseEntity.ok("Conformity updated successfully");
    }

}

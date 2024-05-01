package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.entity.Produit;
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
    public ResponseEntity<List<ControlChecklist>> getChecklistByProduit(@PathVariable Long idProduit){
        List<ControlChecklist> checklists = controlCheckListService.retrieveByProduit(idProduit);
        return ResponseEntity.ok(checklists);
    }
    @GetMapping("getByIdControl/{idControl}")
    public ResponseEntity<List<ControlChecklist>> getChecklistByControl(@PathVariable Long idControl){
        List<ControlChecklist> checklists = controlCheckListService.retrieveByControl(idControl);
        return ResponseEntity.ok(checklists);
    }
    @PutMapping("/updateConformity/{itemId}")
    public ResponseEntity<ControlChecklist> updateConformity(
            @PathVariable Long itemId,
            @RequestParam boolean conformity
    ){
        ControlChecklist updatedItem = controlCheckListService.updateConformity(itemId, conformity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("delete/{idControlChecklist}")
    public ResponseEntity<List<ControlChecklist>> deleteProcesschecklist(@PathVariable Long idControlChecklist) {
        List<ControlChecklist> listChecklistProduits =  controlCheckListService.deleteControlChecklist(idControlChecklist);
        return ResponseEntity.ok(listChecklistProduits);
    }
}

package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.service.ControlDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/controlDefect")
public class ControlDefectController {

    @Autowired
    ControlDefectService controlDefectService;

    @GetMapping("getAll")
    public ResponseEntity<List<ControlDefect>> getAllDeffect() {
        List<ControlDefect> ListDefect = controlDefectService.getAllDeffect();
        return ResponseEntity.ok(ListDefect);
    }

    @PostMapping("/add")
    public ResponseEntity<ControlDefect> defectadd(@RequestBody ControlDefect controlDefect) {
        ControlDefect createDefect = controlDefectService.defectadd(controlDefect);
        return ResponseEntity.ok(createDefect);
    }

    @DeleteMapping("delete/{defectId}")
    public ResponseEntity<List<ControlDefect>> deleteProduit(@PathVariable Long defectId) {
        List<ControlDefect> ListControlDefect =  controlDefectService.deleteDefect(defectId);
        return ResponseEntity.ok(ListControlDefect);
    }

    @PutMapping("/update/{id}")

    public ControlDefect updateDefect(@PathVariable Long id, @RequestBody ControlDefect updateDefect) {
        return controlDefectService.updateDefect(id, updateDefect);
    }
}

package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.QualityControl;
import com.tim.gestionqualite.payloads.ControlResponse;
import com.tim.gestionqualite.payloads.QualityControlRequest;
import com.tim.gestionqualite.service.QualiyControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/qualiyControl")
public class QualityControlController {

    @Autowired
    QualiyControlService qualiyControlService;

    public QualityControlController(QualiyControlService qualiyControlService) {
        this.qualiyControlService = qualiyControlService;
    }
    //http://localhost:8089/qualiyControl/retrieve-all-qualiyControl
    @GetMapping("/retrieve-all-qualiyControl")
    @ResponseBody
    public List<QualityControl> getQualiyControlService() {
        List<QualityControl> listQualiyControl = qualiyControlService.retrieveAllQualityControls();
        return listQualiyControl;
    }
    //http://localhost:8089/qualiyControl/get/{controlId}
    @GetMapping("get/{controlId}")
    public ResponseEntity<Optional<QualityControl>> getQualityControlById(@PathVariable Long controlId) {
        Optional<QualityControl> qualityControl = qualiyControlService.retrieveControlById(controlId);
        return ResponseEntity.ok(qualityControl);
    }
    @PostMapping("add")
    public ResponseEntity<ControlResponse> addQualityControl(@RequestBody QualityControlRequest request) {
        ControlResponse addedControlResponse = qualiyControlService.addQualityControl(request.getQualityControl() , request.getUserId(), request.getProduitId(), request.getChecklistIds());
        return ResponseEntity.ok(addedControlResponse);
    }
    @DeleteMapping("delete/{controlId}")
    public ResponseEntity<String> deleteControl(@PathVariable Long controlId) {
        qualiyControlService.deleteControl(controlId);
        return ResponseEntity.ok("control deleted successfully");
    }

    @PutMapping("update/{controlId}")
    public ResponseEntity<ControlResponse> updateControlResponse(@PathVariable Long controlId, @RequestBody QualityControlRequest request) {
        ControlResponse control = qualiyControlService.updateQualityControl(controlId, request.getQualityControl(), request.getProduitId(), request.getChecklistIds());
        return ResponseEntity.ok(control);
    }
    @GetMapping("getUserControls/{userId}")
    public ResponseEntity <List<QualityControl>> getUserControls(@PathVariable Long userId) {
        List<QualityControl> controls = qualiyControlService.getQualityControlsByUserId(userId);
        return ResponseEntity.ok(controls);
    }
}

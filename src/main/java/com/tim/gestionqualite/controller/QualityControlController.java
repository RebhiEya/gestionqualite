package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.QualityControl;
import com.tim.gestionqualite.payloads.QualityControlRequest;
import com.tim.gestionqualite.service.QualiyControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/qualiyControl")
public class QualityControlController {

    @Autowired
    QualiyControlService qualiyControlService;

    public QualityControlController(QualiyControlService qualiyControlService) {
        this.qualiyControlService = qualiyControlService;
    }

    @GetMapping("/retrieve-all-qualiyControl")
    @ResponseBody
    public List<QualityControl> getQualiyControlService() {
        List<QualityControl> listQualiyControl = qualiyControlService.retrieveAllQualityControls();
        return listQualiyControl;
    }


    @PostMapping("/add-qualiyControl")
    @ResponseBody
    public QualityControl addQualityControlWithChecklists(@RequestBody QualityControlRequest request) {
        QualityControl qualityControl = qualiyControlService.addQualityControl(request.getQualityControl() , request.getControlCheckLists(),request.getControlDefect());
        return qualityControl;
    }
    @PutMapping("/update-qualiyControl/{id}")
    public QualityControl updateQualityControlWithChecklistsAndDefects(@PathVariable Long qualityControlId, @RequestBody QualityControlRequest request) {
            return qualiyControlService.updateQualityControl(
                    qualityControlId,
                    request.getQualityControl(),
                    request.getControlCheckLists(),
                    request.getControlDefect()
            );
    }
    @DeleteMapping("/delete-qualiyControl/{id}")
    public void deleteQualityControl(@PathVariable Long id) {
        qualiyControlService.deleteQualityControl(id);
    }
    @DeleteMapping("/{qualityControlId}/control-checklist/{controlCheckListId}")
    public void deleteControlCheckListFromQualityControl(@PathVariable Long qualityControlId,
                                                         @PathVariable Long controlCheckListId) {
        qualiyControlService.deleteControlCheckListFromQualityControl(qualityControlId, controlCheckListId);
    }
    @DeleteMapping("/{qualityControlId}/control-checklist/{controlCheckListId}")
    public void deleteControlDefectFromQualityControl(@PathVariable Long qualityControlId,
                                                         @PathVariable Long controlDefectId) {
        qualiyControlService.deleteDefectFromQualityControl(qualityControlId, controlDefectId);
    }
}

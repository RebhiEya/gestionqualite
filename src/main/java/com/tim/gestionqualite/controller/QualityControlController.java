package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.QualityControl;
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


    @GetMapping("/retrieve-all-qualiyControl")
    @ResponseBody
    public List<QualityControl> getQualiyControlService() {
        List<QualityControl> listQualiyControl = qualiyControlService.retrieveAllQualityControls();
        return listQualiyControl;
    }


    @PostMapping("/add-qualiyControl")
    @ResponseBody
    public QualityControl addQualityControlWithChecklists(@RequestBody QualityControl q) {
        QualityControl qualityControl = qualiyControlService.addQualityControlWithChecklists(q , q.getControlCheckList());
        return qualityControl;
    }
}

package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.ControlCheckList;
import com.tim.gestionqualite.service.ControlCheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/controlCheckList")
public class ControlCheckListController {

    @Autowired
    ControlCheckListService controlCheckListService;
    @GetMapping("/retrieve-all-controlCheckList/{qualiyControl-id}")
    @ResponseBody
    public List<ControlCheckList> getControlCheckList(@PathVariable("qualiyControl-id") Long idQualityControl) {
        List<ControlCheckList> listControlCheckList = controlCheckListService.retrieveAllControlCheckListByIdControl(idQualityControl);
        return listControlCheckList;
    }

}

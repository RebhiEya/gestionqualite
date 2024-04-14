package com.tim.gestionqualite.controller;


import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.service.ControlDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/controlDefect")
public class ControlDefectController {

    @Autowired
    ControlDefectService controlDefectService;

}

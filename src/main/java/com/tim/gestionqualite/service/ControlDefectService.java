package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.repository.ControlDefectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlDefectService {

    @Autowired
    ControlDefectRepository controlDefectRepository;

    public List<ControlDefect> retrieveAllControlCheckListByIdControl(Long idQualityControl) {
        return (List<ControlDefect>) controlDefectRepository.findByQualityControlIdQualityControl(idQualityControl);
    }
}

package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlCheckList;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlCheckListService {

    @Autowired
    ControlCheckListRepository controlCheckListRepository;

    public List<ControlCheckList> retrieveAllControlCheckListByIdControl(Long idQualityControl) {
        return (List<ControlCheckList>) controlCheckListRepository.findByQualityControlIdQualityControl(idQualityControl);
    }
}

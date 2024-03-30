package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlCheckList;
import com.tim.gestionqualite.entity.QualityControl;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualiyControlService {
    @Autowired
    QualityControlRepository qualityControlRepository;
    @Autowired
    ControlCheckListRepository controlCheckListRepository;

    public List<QualityControl> retrieveAllQualityControls() {
        return (List<QualityControl>) qualityControlRepository.findAll();
    }

    public QualityControl addQualityControl(QualityControl q) {
        return qualityControlRepository.save(q);
    }

    @Transactional
    public QualityControl addQualityControlWithChecklists(QualityControl qualityControl, List<ControlCheckList> controlCheckLists) {
        // Save the QualityControl entity to generate an ID
        QualityControl savedQualityControl = qualityControlRepository.save(qualityControl);

        // Set the QualityControl reference for each ControlCheckList
        for (ControlCheckList controlCheckList : controlCheckLists) {
            controlCheckList.setQualityControl(savedQualityControl);
        }

        // Save the list of ControlCheckList entities
        controlCheckListRepository.saveAll(controlCheckLists);
        return savedQualityControl;
    }
}

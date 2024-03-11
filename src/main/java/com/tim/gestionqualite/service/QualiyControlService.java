package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.QualityControl;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualiyControlService {
    @Autowired
    QualityControlRepository qualityControlRepository;

    public List<QualityControl> retrieveAllQualityControls() {
        return (List<QualityControl>) qualityControlRepository.findAll();
    }

    public QualityControl addQualityControl(QualityControl q) {
        return qualityControlRepository.save(q);
    }
}

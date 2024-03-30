package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlCheckList;
import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.entity.QualityControl;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ControlDefectRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QualiyControlService {
    @Autowired
    QualityControlRepository qualityControlRepository;
    @Autowired
    ControlCheckListRepository controlCheckListRepository;
    @Autowired
    ControlDefectRepository controlDefectRepository;

    public List<QualityControl> retrieveAllQualityControls() {
        return (List<QualityControl>) qualityControlRepository.findAll();
    }

    public QualityControl addQualityControl(QualityControl q) {
        return qualityControlRepository.save(q);
    }


    @Transactional
    public QualityControl addQualityControl(QualityControl qualityControl,
                                            List<ControlCheckList> controlCheckLists,
                                            List<ControlDefect> controlDefects) {
        // Save the QualityControl entity to generate an ID
        QualityControl savedQualityControl = qualityControlRepository.save(qualityControl);

        // Add ControlCheckList entities to QualityControl
        for (ControlCheckList controlCheckList : controlCheckLists) {
            controlCheckList.getQualityControls().add(savedQualityControl);
        }

        // Add ControlDefect entities to QualityControl
        for (ControlDefect controlDefect : controlDefects) {
            controlDefect.getQualityControls().add(savedQualityControl);
        }

        return savedQualityControl;
    }

    @Transactional
    public QualityControl updateQualityControl(Long qualityControlId, QualityControl updatedQualityControl,
                                               List<ControlCheckList> updatedControlCheckLists,
                                               List<ControlDefect> updatedControlDefects) {
        // Find the existing QualityControl entity
        QualityControl existingQualityControl = qualityControlRepository.findById(qualityControlId)
                .orElseThrow(() -> new RuntimeException("QualityControl not found with id: " + qualityControlId));

        // Update fields of the existing QualityControl with the updatedQualityControl
        existingQualityControl.setReference(updatedQualityControl.getReference());
        existingQualityControl.setInternalReference(updatedQualityControl.getInternalReference());
        existingQualityControl.setDate(updatedQualityControl.getDate());
        existingQualityControl.setState(updatedQualityControl.getState());
        existingQualityControl.setDescription(updatedQualityControl.getDescription());

        // Update the association with ControlCheckList
        existingQualityControl.getControlCheckLists().clear();
        for (ControlCheckList updatedControlCheckList : updatedControlCheckLists) {
            updatedControlCheckList.getQualityControls().add(existingQualityControl);
            existingQualityControl.getControlCheckLists().add(updatedControlCheckList);
        }

        // Update the association with ControlDefect
        existingQualityControl.getControlDefect().clear();
        for (ControlDefect updatedControlDefect : updatedControlDefects) {
            updatedControlDefect.getQualityControls().add(existingQualityControl);
            existingQualityControl.getControlDefect().add(updatedControlDefect);
        }

        // Save and return the updated QualityControl entity
        return qualityControlRepository.save(existingQualityControl);
    }

    @Transactional
    public void deleteQualityControl(Long qualityControlId) {
        // Find the QualityControl entity
        Optional<QualityControl> qualityControlOptional = qualityControlRepository.findById(qualityControlId);
        if (qualityControlOptional.isPresent()) {
            QualityControl qualityControl = qualityControlOptional.get();

            // Clear associations with ControlCheckList
            for (ControlCheckList controlCheckList : qualityControl.getControlCheckLists()) {
                controlCheckList.getQualityControls().remove(qualityControl);
            }
            qualityControl.getControlCheckLists().clear();

            // Clear associations with ControlDefect
            for (ControlDefect controlDefect : qualityControl.getControlDefect()) {
                controlDefect.getQualityControls().remove(qualityControl);
            }
            qualityControl.getControlDefect().clear();

            // Delete the QualityControl entity
            qualityControlRepository.delete(qualityControl);
        } else {
            throw new RuntimeException("QualityControl not found with id: " + qualityControlId);
        }
    }

    @Transactional
    public void deleteControlCheckListFromQualityControl(Long qualityControlId, Long controlCheckListId) {
        // Find the QualityControl entity
        QualityControl qualityControl = qualityControlRepository.findById(qualityControlId)
                .orElseThrow(() -> new RuntimeException("QualityControl not found with id: " + qualityControlId));

        // Find the ControlCheckList entity
        ControlCheckList controlCheckList = controlCheckListRepository.findById(controlCheckListId)
                .orElseThrow(() -> new RuntimeException("ControlCheckList not found with id: " + controlCheckListId));

        // Remove the ControlCheckList from the QualityControl
        qualityControl.getControlCheckLists().remove(controlCheckList);

        // Remove the QualityControl from the ControlCheckList
        controlCheckList.getQualityControls().remove(qualityControl);

        // Save the updated entities
        qualityControlRepository.save(qualityControl);
        controlCheckListRepository.save(controlCheckList);
    }
    @Transactional
    public void deleteDefectFromQualityControl(Long qualityControlId, Long controlDefectId) {
        // Find the QualityControl entity
        QualityControl qualityControl = qualityControlRepository.findById(qualityControlId)
                .orElseThrow(() -> new RuntimeException("QualityControl not found with id: " + qualityControlId));

        // Find the ControlCheckList entity
        ControlDefect controlDefect = controlDefectRepository.findById(controlDefectId)
                .orElseThrow(() -> new RuntimeException("ControlDefect not found with id: " + controlDefectId));

        // Remove the ControlCheckList from the QualityControl
        qualityControl.getControlCheckLists().remove(controlDefect);

        // Remove the QualityControl from the ControlCheckList
        controlDefect.getQualityControls().remove(qualityControl);

        // Save the updated entities
        qualityControlRepository.save(qualityControl);
        controlDefectRepository.save(controlDefect);
    }
}

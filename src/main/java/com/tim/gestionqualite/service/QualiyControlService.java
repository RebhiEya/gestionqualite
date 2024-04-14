package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.payloads.ControlResponse;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ControlDefectRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QualiyControlService {
    @Autowired
    QualityControlRepository qualityControlRepository;
    @Autowired
    ControlCheckListRepository controlCheckListRepository;

    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ControlDefectRepository controlDefectRepository;

    public List<QualityControl> retrieveAllQualityControls() {
        return (List<QualityControl>) qualityControlRepository.findAll();
    }

    @Transactional
    public ControlResponse addQualityControl(QualityControl qualityControl, Long produitId, Set<Long> checklistIds) {
        ControlResponse controlResponse = new ControlResponse();
        // Step 1: Find the Process by processId
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit with ID " + produitId + " not found"));
        controlResponse.setProduit(produit);
        // Step 3: Create a Set<AuditProcessChecklist> for each ProcessChecklist
        Set<ProduitControlChecklist> produitControlChecklists = new HashSet<>();
        Set<ControlChecklist> selectedChecklists = new HashSet<>();
        // Add ControlDefect entities to QualityControl
        for (ControlDefect controlDefect : qualityControl.getControlDefect()) {
            controlDefect.getQualityControls().add(qualityControl);
        }
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {

                ControlChecklist checklist = controlCheckListRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                selectedChecklists.add(checklist);

                ProduitControlChecklist produitControlChecklist = new ProduitControlChecklist();
                ProduitControlChecklistId produitControlChecklistId = new ProduitControlChecklistId();

                produitControlChecklistId.setControlId(null);
                produitControlChecklistId.setProduitId(produit.getIdProduit());
                produitControlChecklistId.setChecklistId(checklistId);

                produitControlChecklist.setId(produitControlChecklistId);
                produitControlChecklist.setControl(qualityControl);
                produitControlChecklist.setProduit(produit);
                produitControlChecklist.setProduitChecklist(checklist);

                produitControlChecklists.add(produitControlChecklist);
            }
        }
        // Step 4: Set the AuditProcessChecklist in the Audit
        qualityControl.setControlChecklist(produitControlChecklists);

        // Step 5: Save the Audit with ProcessChecklist
        qualityControlRepository.save(qualityControl);

        controlResponse.setQualityControl(qualityControl);
        controlResponse.setChecklist(selectedChecklists);

        return controlResponse;
    }

    @Transactional
    public ControlResponse updateQualityControl(Long controlId, QualityControl updatedControl, Long produitId, Set<Long> checklistIds) {
        // Step 1: Find the existing Audit by auditId
        QualityControl qualityControl = qualityControlRepository.findById(controlId)
                .orElseThrow(() -> new IllegalArgumentException("Audit with ID " + controlId + " not found"));

        // Step 2: Update the existing Audit with new details
        qualityControl.setControlDefect(updatedControl.getControlDefect());
        qualityControl.setReference(updatedControl.getReference());
        qualityControl.setDate(updatedControl.getDate());
        qualityControl.setInternalReference(updatedControl.getInternalReference());
        qualityControl.setDescription(updatedControl.getDescription());
        qualityControl.setState(updatedControl.getState());
        // Update other fields as needed

        // Step 3: Find the Process by processId
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit with ID " + produitId + " not found"));

        // Step 4: Retrieve the existing ProcessChecklists associated with the Audit
        Set<ProduitControlChecklist> existingProduitControlChecklists = qualityControl.getControlChecklist();

        // Step 5: Create a Set<AuditProcessChecklist> for each selected ProcessChecklist
        Set<ProduitControlChecklist> produitControlChecklists = new HashSet<>();
        Set<ControlChecklist> selectedChecklists = new HashSet<>();
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {
                ControlChecklist checklist = controlCheckListRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                selectedChecklists.add(checklist);

                // Check if the selected checklist is already associated with the Audit
                boolean existsInControl = existingProduitControlChecklists.stream()
                        .anyMatch(a -> a.getProduitChecklist().getIdControlCheckList().equals(checklistId));

                // If not already associated, create a new AuditProcessChecklist
                if (!existsInControl) {
                    ProduitControlChecklist produitControlChecklist = new ProduitControlChecklist();
                    ProduitControlChecklistId produitControlChecklistId = new ProduitControlChecklistId();

                    produitControlChecklistId.setControlId(controlId); // Use the provided auditId
                    produitControlChecklistId.setProduitId(produit.getIdProduit());
                    produitControlChecklistId.setChecklistId(checklistId);

                    produitControlChecklist.setId(produitControlChecklistId);
                    produitControlChecklist.setControl(qualityControl);
                    produitControlChecklist.setProduit(produit);
                    produitControlChecklist.setProduitChecklist(checklist);

                    produitControlChecklists.add(produitControlChecklist);
                }
            }
        }

        // Step 6: Set the updated AuditProcessChecklists in the Audit
        qualityControl.getControlChecklist().clear(); // Clear existing associations
        qualityControl.getControlChecklist().addAll(produitControlChecklists);

        // Step 7: Save the updated Audit with ProcessChecklists
        qualityControlRepository.save(qualityControl);

        // Step 8: Set the response fields
        ControlResponse controlResponse = new ControlResponse();
        controlResponse.setQualityControl(qualityControl);
        controlResponse.setProduit(produit);
        controlResponse.setChecklist(selectedChecklists);

        return controlResponse;
    }

    public Optional<QualityControl> retrieveAuditById(Long auditId) {
        return  qualityControlRepository.findById(auditId);

    }
    public void deleteControl(Long controlId) {
        // Vérifiez d'abord si l'audit existe
        if (!qualityControlRepository.existsById(controlId)) {
            throw new IllegalArgumentException("Audit not found");
        }

        // Supprimez l'audit de la base de données
        qualityControlRepository.deleteById(controlId);
    }
}

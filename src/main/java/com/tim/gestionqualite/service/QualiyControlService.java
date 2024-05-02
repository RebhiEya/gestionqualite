package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.payloads.ControlResponse;
import com.tim.gestionqualite.repository.*;
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

    @Autowired
    UserRepository userRepository;

    public List<QualityControl> retrieveAllQualityControls() {
        return (List<QualityControl>) qualityControlRepository.findAll();
    }

    @Transactional
    public ControlResponse addQualityControl(QualityControl qualityControl,Long userId, Long produitId, Set<Long> checklistIds) {
        ControlResponse controlResponse = new ControlResponse();
        // Step 1: Find the User by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        qualityControl.setUser(user);
        // Step 1: Find the Process by processId
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit with ID " + produitId + " not found"));
        controlResponse.setProduit(produit);
        // Step 3: Create a Set<ProduitControlChecklist> for each controlChecklist
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
                produitControlChecklist.setConformity(null);

                produitControlChecklists.add(produitControlChecklist);
            }
        }
        // Step 4: Set the ProduitControlChecklist in the control
        qualityControl.setControlChecklist(produitControlChecklists);

        // Step 5: Save the control with controlChecklist
        qualityControlRepository.save(qualityControl);

        controlResponse.setQualityControl(qualityControl);
        controlResponse.setChecklist(selectedChecklists);

        return controlResponse;
    }

    @Transactional
    public ControlResponse updateQualityControl(Long controlId, QualityControl updatedControl, Long produitId, Set<Long> checklistIds) {
        // Step 1: Find the existing control by controlId
        QualityControl qualityControl = qualityControlRepository.findById(controlId)
                .orElseThrow(() -> new IllegalArgumentException("Control with ID " + controlId + " not found"));

        // Step 2: Update the existing Control with new details
        qualityControl.setControlDefect(updatedControl.getControlDefect());
        qualityControl.setReference(updatedControl.getReference());
        qualityControl.setDate(updatedControl.getDate());
        qualityControl.setInternalReference(updatedControl.getInternalReference());
        qualityControl.setDescription(updatedControl.getDescription());
        qualityControl.setState(updatedControl.getState());
        // Update other fields as needed

        // Step 3: Find the control by produitId
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit with ID " + produitId + " not found"));

        // Step 4: Retrieve the existing ProcessChecklists associated with the control
        Set<ProduitControlChecklist> existingProduitControlChecklists = qualityControl.getControlChecklist();

        // Step 5: Create a Set<ProduitControlChecklist> for each selected ProcessChecklist
        Set<ProduitControlChecklist> produitControlChecklists = new HashSet<>();
        Set<ControlChecklist> selectedChecklists = new HashSet<>();
        if (checklistIds != null && !checklistIds.isEmpty()) {
            for (Long checklistId : checklistIds) {
                ControlChecklist checklist = controlCheckListRepository.findById(checklistId)
                        .orElseThrow(() -> new IllegalArgumentException("Checklist not found"));

                selectedChecklists.add(checklist);

                // Check if the selected checklist is already associated with the control
                boolean existsInControl = existingProduitControlChecklists.stream()
                        .anyMatch(a -> a.getProduitChecklist().getIdControlCheckList().equals(checklistId));

                // If not already associated, create a new ProduitControlChecklist
                if (!existsInControl) {
                    ProduitControlChecklist produitControlChecklist = new ProduitControlChecklist();
                    ProduitControlChecklistId produitControlChecklistId = new ProduitControlChecklistId();

                    produitControlChecklistId.setControlId(controlId); // Use the provided controlId
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

        // Step 6: Set the updated ProduitControlChecklist in the control
        qualityControl.getControlChecklist().clear(); // Clear existing associations
        qualityControl.getControlChecklist().addAll(produitControlChecklists);

        // Step 7: Save the updated control with controlChecklists
        qualityControlRepository.save(qualityControl);

        // Step 8: Set the response fields
        ControlResponse controlResponse = new ControlResponse();
        controlResponse.setQualityControl(qualityControl);
        controlResponse.setProduit(produit);
        controlResponse.setChecklist(selectedChecklists);

        return controlResponse;
    }

    public Optional<QualityControl> retrieveControlById(Long controlId) {
        return  qualityControlRepository.findById(controlId);

    }
    public void deleteControl(Long controlId) {
        // Vérifiez d'abord si le control existe
        if (!qualityControlRepository.existsById(controlId)) {
            throw new IllegalArgumentException("Control not found");
        }

        // Supprimez le control de la base de données
        qualityControlRepository.deleteById(controlId);
    }

    public List<QualityControl> getQualityControlsByUserId(Long userId) {
        return qualityControlRepository.findByUserIdUser(userId);
    }
}

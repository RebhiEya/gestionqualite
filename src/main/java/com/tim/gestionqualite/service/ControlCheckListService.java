package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.ProcessChecklist;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ControlCheckListService {

    @Autowired
    ControlCheckListRepository controlCheckListRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    QualityControlRepository qualityControlRepository;

    public List<ControlChecklist> retrieveByProduit(Long idProduit) {
        produitRepository.findById(idProduit).orElseThrow(() -> new IllegalArgumentException("Produit not found"));

        return controlCheckListRepository.findByProduitsIdProduit(idProduit);
    }
    public List<ControlChecklist> retrieveByControl(Long idControl) {
        qualityControlRepository.findById(idControl).orElseThrow(() -> new IllegalArgumentException("Control not found"));

        return controlCheckListRepository.findByProduitControlChecklistIdControlId(idControl);
    }

    public ControlChecklist updateConformity(Long itemId, boolean conformity) {
        ControlChecklist checklistItem = controlCheckListRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Control Checklist Item not found"));

        // Mettre à jour la conformité de l'élément de la checklist
        checklistItem.setConformity(conformity);

        // Enregistrer les modifications dans la base de données
        return controlCheckListRepository.save(checklistItem);
    }
    public List<ControlChecklist> deleteControlChecklist(Long controlChecklistId) {
        Optional<ControlChecklist> optionalProcessChecklist = controlCheckListRepository.findById(controlChecklistId);
        if (optionalProcessChecklist.isPresent()) {
            ControlChecklist produitChecklist = optionalProcessChecklist.get();
            produitChecklist.getProduits().forEach(produit -> produit.getProduitChecklist().remove(produitChecklist));
            controlCheckListRepository.delete(produitChecklist);
            return controlCheckListRepository.findAll();
        } else {
            throw new NoSuchElementException("Control Checklist not found with id: " + controlChecklistId);
        }
    }
}

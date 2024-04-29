package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}

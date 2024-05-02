package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.ProduitControlChecklist;
import com.tim.gestionqualite.payloads.ChecklistConformityDTO;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ProduitControlChecklistRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import com.tim.gestionqualite.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ControlCheckListService {

    @Autowired
    ControlCheckListRepository controlCheckListRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ProduitControlChecklistRepository produitControlChecklistRepository;


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

    public void updateConformityForChecklist(Long controlId, Long checklistId, boolean newConformityValue) {
        ProduitControlChecklist produitControlChecklist = produitControlChecklistRepository.findByControlIdQualityControlAndProduitChecklistIdControlCheckList(controlId, checklistId);

        if (produitControlChecklist != null) {
            produitControlChecklist.setConformity(newConformityValue);
            produitControlChecklistRepository.save(produitControlChecklist);
        } else {
            throw new IllegalArgumentException("association not found");
        }
    }
    public List<ChecklistConformityDTO> getChecklistsAndConformityByControlId(Long controlId) {
        List<ProduitControlChecklist> produitControlChecklists = produitControlChecklistRepository.findByControlIdQualityControl(controlId);
        List<ChecklistConformityDTO> checklistConformityDTOList = new ArrayList<>();

        for (ProduitControlChecklist produitControlChecklist : produitControlChecklists) {
            ControlChecklist controlChecklist = controlCheckListRepository.findById(produitControlChecklist.getId().getChecklistId()).orElse(null);
            if (controlChecklist != null) {
                ChecklistConformityDTO checklistConformityDTO = new ChecklistConformityDTO(controlChecklist, produitControlChecklist.getConformity());
                checklistConformityDTOList.add(checklistConformityDTO);
            }
        }

        return checklistConformityDTOList;
    }

}

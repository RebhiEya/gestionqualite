package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlCheckListService {

    @Autowired
    ControlCheckListRepository controlCheckListRepository;
    @Autowired
    ProduitRepository produitRepository;

    public List<ControlChecklist> retrieveByProduit(Long idProduit) {
        produitRepository.findById(idProduit).orElseThrow(() -> new IllegalArgumentException("Produit not found"));

        return  controlCheckListRepository.findByProduitsIdProduit(idProduit);
    }

}

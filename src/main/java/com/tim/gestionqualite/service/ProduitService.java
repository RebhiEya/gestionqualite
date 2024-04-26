package com.tim.gestionqualite.service;


import com.tim.gestionqualite.entity.*;
import com.tim.gestionqualite.repository.ControlCheckListRepository;
import com.tim.gestionqualite.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ControlCheckListRepository controlChecklistRepository;

    public List<Produit> retrieveAllProduits() {
        return  produitRepository.findAll();
    }

    public List<Produit> deleteProduit(Long produitId) {
        // Vérifiez d'abord si le produit existe
        if (!produitRepository.existsById(produitId)) {
            throw new IllegalArgumentException("Produit not found");
        }
        // Supprimez le produit de la base de données
        produitRepository.deleteById(produitId);
        // renvoyer la liste des produits
        return produitRepository.findAll();
    }


    public Optional<Produit> retrieveProduitById(Long auditId) {
        return  produitRepository.findById(auditId);
    }
    public Produit addProduitWithoutChecklist(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit addProduitWithChecklists(Produit produit, List<Long> checklistIds) {
        Produit createdProduit = produitRepository.save(produit);
        if (checklistIds != null && !checklistIds.isEmpty()) {
            List<ControlChecklist> checklists = controlChecklistRepository.findAllById(checklistIds);
            if (checklists.isEmpty()) {
                throw new IllegalArgumentException("Checklists not found");
            }
            createdProduit.getProduitChecklist().addAll(checklists);
            return produitRepository.save(createdProduit);
        }
        return produitRepository.save(createdProduit);

    }

    public Produit addChecklistAndAssignToProduit(Long processId, ControlChecklist controlChecklist) {
        controlChecklist = controlChecklistRepository.save(controlChecklist);
        Produit produit = produitRepository.findById(processId).orElseThrow(() -> new IllegalArgumentException("produit not found"));
        produit.getProduitChecklist().add(controlChecklist);
        produitRepository.save(produit);
        return produit;
    }
    public Produit updateProduit(Long idProduit, Produit updatedProduit) {
        Produit existingProduit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + idProduit));

        // Mettre à jour les champs que vous souhaitez
        existingProduit.setCategory(updatedProduit.getCategory());
        existingProduit.setDesignation(updatedProduit.getDesignation());
        existingProduit.setReference(updatedProduit.getReference());
        existingProduit.setFamille(updatedProduit.getFamille());

        return produitRepository.save(existingProduit);
    }
}

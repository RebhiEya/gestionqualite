package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.Produit;

import java.util.List;

public class ProduitRequest {

    private Produit produit;
    private List<Long> checklistIds;

    // Getters and setters
    public Produit getProduit() {
        return produit;
    }

    public void setProcess(Produit produit) {
        this.produit = produit;
    }

    public List<Long> getChecklistIds() {
        return checklistIds;
    }

    public void setChecklistIds(List<Long> checklistIds) {
        this.checklistIds = checklistIds;
    }
}

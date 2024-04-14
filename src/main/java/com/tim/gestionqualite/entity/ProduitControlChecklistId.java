package com.tim.gestionqualite.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProduitControlChecklistId {

    @Column(name = "control_id")
    private Long controlId;

    @Column(name = "produit_id")
    private Long produitId;

    @Column(name = "checklist_id")
    private Long checklistId;

    public Long getControlId() {
        return controlId;
    }

    public void setControlId(Long controlId) {
        this.controlId = controlId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }
}

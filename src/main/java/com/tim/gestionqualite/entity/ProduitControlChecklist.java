package com.tim.gestionqualite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "produit_control_checklist")
public class ProduitControlChecklist {

    @EmbeddedId
    private ProduitControlChecklistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("controlId")
    private QualityControl control;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produitId")
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("checklistId")
    private ControlChecklist produitChecklist;

    public ProduitControlChecklistId getId() {
        return id;
    }

    public void setId(ProduitControlChecklistId id) {
        this.id = id;
    }

    public QualityControl getControl() {
        return control;
    }

    public void setControl(QualityControl control) {
        this.control = control;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public ControlChecklist getProduitChecklist() {
        return produitChecklist;
    }

    public void setProduitChecklist(ControlChecklist produitChecklist) {
        this.produitChecklist = produitChecklist;
    }
}

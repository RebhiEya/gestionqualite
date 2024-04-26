package com.tim.gestionqualite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Controlchecklist")
@Entity
public class ControlChecklist implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idControlCheck")
    private Long idControlCheckList;
    private String category;
    private String criteria;
    private String operation;
    private Boolean conformity;
    private String defects;
    private Number measures;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "produitChecklist")
    private Set<ProduitControlChecklist> produitControlChecklist = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "produitChecklist")
    private Set<Produit> produits  = new HashSet<>();

    public Long getIdControlCheckList() {
        return idControlCheckList;
    }

    public void setIdControlCheckList(Long idControlCheckList) {
        this.idControlCheckList = idControlCheckList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Boolean getConformity() {
        return conformity;
    }

    public void setConformity(Boolean conformity) {
        this.conformity = conformity;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public Number getMeasures() {
        return measures;
    }

    public void setMeasures(Number measures) {
        this.measures = measures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProduitControlChecklist> getProduitControlChecklist() {
        return produitControlChecklist;
    }

    public void setProduitControlChecklist(Set<ProduitControlChecklist> produitControlChecklist) {
        this.produitControlChecklist = produitControlChecklist;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}

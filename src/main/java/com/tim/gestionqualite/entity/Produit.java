package com.tim.gestionqualite.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produit")
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduit")
    private Long idProduit;
    private String category;
    private String designation;
    private String reference;
    private String Famille;

    @JsonIgnore
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProduitControlChecklist> produitControlChecklist = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "produit_checklist",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "checklist_id")
    )
    private Set<ControlChecklist> produitChecklist = new HashSet<>();
    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFamille() {
        return Famille;
    }

    public void setFamille(String famille) {
        Famille = famille;
    }

    public Set<ProduitControlChecklist> getProduitControlChecklist() {
        return produitControlChecklist;
    }

    public void setProduitControlChecklist(Set<ProduitControlChecklist> produitControlChecklist) {
        this.produitControlChecklist = produitControlChecklist;
    }

    public Set<ControlChecklist> getProduitChecklist() {
        return produitChecklist;
    }

    public void setProduitChecklist(Set<ControlChecklist> produitChecklist) {
        this.produitChecklist = produitChecklist;
    }
}

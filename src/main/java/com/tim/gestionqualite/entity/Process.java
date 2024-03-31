package com.tim.gestionqualite.entity;


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
@Table(name = "Process")
@Entity
public class Process implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProcess")
    private Long idProcess;
    private String nom;
    private String recommendation;
    private String weakness;
    private String strength;

    @ManyToMany(mappedBy = "processes")
    private Set<Audit> audits = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "process_checklist",
            joinColumns = @JoinColumn(name = "process_id"),
            inverseJoinColumns = @JoinColumn(name = "checklist_id")
    )
    private Set<ProcessChecklist> checklists = new HashSet<>();

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Set<Audit> getAudits() {
        return audits;
    }

    public void setAudits(Set<Audit> audits) {
        this.audits = audits;
    }

    public Set<ProcessChecklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(Set<ProcessChecklist> checklists) {
        this.checklists = checklists;
    }
}

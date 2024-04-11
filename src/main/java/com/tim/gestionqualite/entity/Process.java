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
    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AuditProcessChecklist> auditProcessChecklist = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "process_checklist",
            joinColumns = @JoinColumn(name = "process_id"),
            inverseJoinColumns = @JoinColumn(name = "checklist_id")
    )
    private Set<ProcessChecklist> processChecklist = new HashSet<>();

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

    public Set<AuditProcessChecklist> getAuditProcessChecklist() {
        return auditProcessChecklist;
    }

    public void setAuditProcessChecklist(Set<AuditProcessChecklist> auditProcessChecklist) {
        this.auditProcessChecklist = auditProcessChecklist;
    }

    public Set<ProcessChecklist> getProcessChecklist() {
        return processChecklist;
    }

    public void setProcessChecklist(Set<ProcessChecklist> processChecklist) {
        this.processChecklist = processChecklist;
    }
}

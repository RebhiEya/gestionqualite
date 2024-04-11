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
@Table(name = "Processchecklist")
@Entity
public class ProcessChecklist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProcessChecklist")
    private Long idProcessChecklist;
    private String requirement;
    private String category;
    private String state;
    private Boolean conformity;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "processChecklist")
    private Set<AuditProcessChecklist> auditProcessChecklist = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "processChecklist")
    private Set<Process> processes = new HashSet<>();


    public Long getIdProcessChecklist() {
        return idProcessChecklist;
    }

    public void setIdProcessChecklist(Long idProcessChecklist) {
        this.idProcessChecklist = idProcessChecklist;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getConformity() {
        return conformity;
    }

    public void setConformity(Boolean conformity) {
        this.conformity = conformity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AuditProcessChecklist> getAuditProcessChecklist() {
        return auditProcessChecklist;
    }

    public void setAuditProcessChecklist(Set<AuditProcessChecklist> auditProcessChecklist) {
        this.auditProcessChecklist = auditProcessChecklist;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }
}
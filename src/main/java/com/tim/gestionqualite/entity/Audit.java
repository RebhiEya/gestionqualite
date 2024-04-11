package com.tim.gestionqualite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Audit")
@Entity
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAudit")
    private Long idAudit;
    private String category;
    private String designation;
    private Date startDate;
    private Date endDate;
    private String state;
    private String reference;

    @JsonIgnore
    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AuditProcessChecklist> processChecklist = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "audit_team",
            joinColumns = @JoinColumn(name = "audit_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL)
    private List<AuditFile> auditFiles;

    public Long getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(Long idAudit) {
        this.idAudit = idAudit;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<AuditProcessChecklist> getProcessChecklist() {
        return processChecklist;
    }

    public void setProcessChecklist(Set<AuditProcessChecklist> processChecklist) {
        this.processChecklist = processChecklist;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public List<AuditFile> getAuditFiles() {
        return auditFiles;
    }

    public void setAuditFiles(List<AuditFile> auditFiles) {
        this.auditFiles = auditFiles;
    }
}
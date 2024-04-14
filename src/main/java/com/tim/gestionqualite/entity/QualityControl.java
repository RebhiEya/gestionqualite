package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Qualitycontrol")
@Entity
public class QualityControl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQualityControl")
    private Long idQualityControl;
    private String reference;
    private String internalReference;
    private Date date;
    private String state;
    private String description;
    @OneToMany(mappedBy = "control", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProduitControlChecklist> controlChecklist = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "qualitycontrol_defect",
            joinColumns = @JoinColumn(name = "quality_control_id"),
            inverseJoinColumns = @JoinColumn(name = "control_defect_id")
    )
    private List<ControlDefect> controlDefect = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "control_team",
            joinColumns = @JoinColumn(name = "control_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();
    public Long getIdQualityControl() {
        return idQualityControl;
    }

    public void setIdQualityControl(Long idQualityControl) {
        this.idQualityControl = idQualityControl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProduitControlChecklist> getControlChecklist() {
        return controlChecklist;
    }

    public void setControlChecklist(Set<ProduitControlChecklist> controlChecklist) {
        this.controlChecklist = controlChecklist;
    }

    public List<ControlDefect> getControlDefect() {
        return controlDefect;
    }

    public void setControlDefect(List<ControlDefect> controlDefect) {
        this.controlDefect = controlDefect;
    }


    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}

package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "QualityControl")
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
    @ManyToMany
    @JoinTable(
            name = "quality_control_checklist",
            joinColumns = @JoinColumn(name = "quality_control_id"),
            inverseJoinColumns = @JoinColumn(name = "control_checklist_id")
    )
    private List<ControlCheckList> controlCheckLists = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "quality_control_defect",
            joinColumns = @JoinColumn(name = "quality_control_id"),
            inverseJoinColumns = @JoinColumn(name = "control_defect_id")
    )
    private List<ControlDefect> controlDefect = new ArrayList<>();

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

    public List<ControlCheckList> getControlCheckLists() {
        return controlCheckLists;
    }

    public void setControlCheckLists(List<ControlCheckList> controlCheckLists) {
        this.controlCheckLists = controlCheckLists;
    }

    public List<ControlDefect> getControlDefect() {
        return controlDefect;
    }

    public void setControlDefect(List<ControlDefect> controlDefect) {
        this.controlDefect = controlDefect;
    }
}

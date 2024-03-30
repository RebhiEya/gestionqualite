package com.tim.gestionqualite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ControlCheckList")
@Entity
public class ControlCheckList implements Serializable  {
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
    @ManyToMany(mappedBy = "controlCheckLists")
    private List<QualityControl> qualityControls = new ArrayList<>();

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

    public List<QualityControl> getQualityControls() {
        return qualityControls;
    }

    public void setQualityControls(List<QualityControl> qualityControls) {
        this.qualityControls = qualityControls;
    }
}

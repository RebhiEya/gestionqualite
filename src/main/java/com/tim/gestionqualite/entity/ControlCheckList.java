package com.tim.gestionqualite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ControlCheckList")
@Entity
public class ControlCheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idControlCheckList")
    private Long idControlCheckList;
    private String Category;
    private String criteria;
    private String operation;

    private Boolean conformity;

    private String defects;

    private Number measures;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "qualityControl_id")
    private QualityControl qualityControl;

    public Long getIdControlCheckList() {
        return idControlCheckList;
    }

    public void setIdControlCheckList(Long idControlCheckList) {
        this.idControlCheckList = idControlCheckList;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public QualityControl getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(QualityControl qualityControl) {
        this.qualityControl = qualityControl;
    }
}

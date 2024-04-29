package com.tim.gestionqualite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Controldefect")
@Entity
public class ControlDefect implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idControlDefect")
    private Long idControlDefect;
    private String category;
    private String code;
    private String quantity;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "controlDefect")
    private List<QualityControl> qualityControls = new ArrayList<>();


    public Long getIdControlDefect() {
        return idControlDefect;
    }

    public void setIdControlDefect(Long idControlDefect) {
        this.idControlDefect = idControlDefect;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

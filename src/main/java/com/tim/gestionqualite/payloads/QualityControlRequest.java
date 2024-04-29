package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.QualityControl;

import java.util.Set;

public class QualityControlRequest {

    private QualityControl qualityControl;

    private Long produitId;
    private Long userId;

    private Set<Long> checklistIds;

    public QualityControl getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(QualityControl qualityControl) {
        this.qualityControl = qualityControl;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Set<Long> getChecklistIds() {
        return checklistIds;
    }

    public void setChecklistIds(Set<Long> checklistIds) {
        this.checklistIds = checklistIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

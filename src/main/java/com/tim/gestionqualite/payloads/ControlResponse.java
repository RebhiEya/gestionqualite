package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.ControlChecklist;
import com.tim.gestionqualite.entity.Process;
import com.tim.gestionqualite.entity.Produit;
import com.tim.gestionqualite.entity.QualityControl;

import java.util.Set;

public class ControlResponse {

    private QualityControl qualityControl;

    private Produit produit;

    Set<ControlChecklist> checklist;

    public QualityControl getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(QualityControl qualityControl) {
        this.qualityControl = qualityControl;
    }

    public QualityControl getProduit() {
        return qualityControl;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Set<ControlChecklist> getChecklist() {
        return checklist;
    }

    public void setChecklist(Set<ControlChecklist> checklist) {
        this.checklist = checklist;
    }
}

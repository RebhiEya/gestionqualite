package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.ControlCheckList;
import com.tim.gestionqualite.entity.ControlDefect;
import com.tim.gestionqualite.entity.QualityControl;

import java.util.List;

public class QualityControlRequest {


    private QualityControl qualityControl;

    // Getters and setters for qualityControl

    public QualityControl getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(QualityControl qualityControl) {
        this.qualityControl = qualityControl;
    }

    public List<ControlCheckList> getControlCheckLists() {
        return qualityControl.getControlCheckList();
    }

    public void setControlCheckLists(List<ControlCheckList> controlCheckLists) {
        qualityControl.setControlCheckList(controlCheckLists);
    }

    public List<ControlDefect> getControlDefect() {
        return qualityControl.getControlDefect();
    }

    public void setControlDefect(List<ControlDefect> controlDefect) {
        qualityControl.setControlDefect(controlDefect);
    }
}

package com.tim.gestionqualite.payloads;

import java.util.List;

public class AddControlDefectsRequest {


    private List<Long> controlDefects;


    public List<Long> getControlDefects() {
        return controlDefects;
    }

    public void setControlDefects(List<Long> controlDefects) {
        this.controlDefects = controlDefects;
    }
}

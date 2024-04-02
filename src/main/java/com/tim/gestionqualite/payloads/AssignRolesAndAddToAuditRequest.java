package com.tim.gestionqualite.payloads;

import java.util.List;

public class AssignRolesAndAddToAuditRequest {

    private List<Long> userIds ;
    private Boolean isAuditeur ;

    private Boolean isController ;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Boolean getAuditeur() {
        return isAuditeur;
    }

    public void setAuditeur(Boolean auditeur) {
        isAuditeur = auditeur;
    }

    public Boolean getController() {
        return isController;
    }

    public void setController(Boolean controller) {
        isController = controller;
    }

}

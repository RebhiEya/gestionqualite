package com.tim.gestionqualite.payloads;

import com.tim.gestionqualite.entity.Audit;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuditProcessRequest {

    private Audit audit ;

    private  Long processId ;

    private Set<Long> checklistIds ;


}

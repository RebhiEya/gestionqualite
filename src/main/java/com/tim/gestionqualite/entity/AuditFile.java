package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "AuditFile")
@Entity
@Getter
@Setter
public class AuditFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAuditFile")
    private Long idFile;
    private String name;
    private String description;
    private String src;

    @ManyToOne
    @JoinColumn(name = "audit_id")
    private Audit audit;
}

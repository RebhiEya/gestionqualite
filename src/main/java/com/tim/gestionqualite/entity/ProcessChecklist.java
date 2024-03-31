package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ProcessChecklist")
@Entity
public class ProcessChecklist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProcessChecklist")
    private Long idProcessChecklist;
    private String requirement;
    private String category;
    private String state;
    private Boolean conformity;
    private String description;
    @ManyToMany(mappedBy = "checklists")
    private Set<Audit> audits = new HashSet<>();

    @ManyToMany(mappedBy = "checklists")
    private Set<Process> processes = new HashSet<>();
}

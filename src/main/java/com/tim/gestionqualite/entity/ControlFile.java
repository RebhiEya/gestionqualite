package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Controlfile")
@Entity
@Getter
@Setter
public class ControlFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idControlFile")
    private Long idFile;
    private String name;
    private String description;
    private String src;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private QualityControl qualityControl;
}

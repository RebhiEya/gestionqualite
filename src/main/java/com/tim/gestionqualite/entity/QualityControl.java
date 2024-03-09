package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "QualityControl")
@Entity
public class QualityControl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQualityControl")
    private Long idQualityControl;
    private String reference;
    private String internalReference;
    private Date date;
    private String state;
    private String description;

}

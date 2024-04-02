package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Team")
@Entity
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy =
            GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleName role;
}
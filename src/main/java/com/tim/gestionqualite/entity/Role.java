package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Role")
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

    public RoleName getRole() {
        return role;
    }
}

package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;



@AllArgsConstructor
@Table(name = "Role")
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy =
            GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}

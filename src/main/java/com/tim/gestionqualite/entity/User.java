package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "\"User\"")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long idUser;
    private String firstName;
    private String lastName;
    private String matricule;
    private String password;
    private String email;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch
            = FetchType.EAGER)
    private Set<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTeamRole> userTeamRoles = new ArrayList<>();
}

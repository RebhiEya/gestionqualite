package com.tim.gestionqualite.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Team")
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeam")
    private Long idTeam;
    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserTeamRole> userTeamRoles = new ArrayList<>();
    @ManyToMany(mappedBy = "teams")
    private Set<Audit> audits = new HashSet<>();

    @ManyToMany(mappedBy = "teams")
    private Set<QualityControl> qualityControls = new HashSet<>();

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }


    public List<UserTeamRole> getUserTeamRoles() {
        return userTeamRoles;
    }

    public void setUserTeamRoles(List<UserTeamRole> userTeamRoles) {
        this.userTeamRoles = userTeamRoles;
    }

    public Set<Audit> getAudits() {
        return audits;
    }

    public void setAudits(Set<Audit> audits) {
        this.audits = audits;
    }
}

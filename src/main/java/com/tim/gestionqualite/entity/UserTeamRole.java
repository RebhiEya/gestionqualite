package com.tim.gestionqualite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "userTeamRole")
@Entity
public class UserTeamRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserTeamRole;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "idTeam", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    private boolean auditeur;

    private boolean controller;

    public UserTeamRole(User user, Team team) {
    }

    public Long getIdUserTeamRole() {
        return idUserTeamRole;
    }

    public void setIdUserTeamRole(Long idUserTeamRole) {
        this.idUserTeamRole = idUserTeamRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isAuditeur() {
        return auditeur;
    }

    public void setAuditeur(boolean auditeur) {
        this.auditeur = auditeur;
    }

    public boolean isController() {
        return controller;
    }

    public void setController(boolean controller) {
        this.controller = controller;
    }
}

package com.tim.gestionqualite.payloads;

import java.util.List;

public class UserInfoResponse {

    private Long idUser;

    private String username;
    private String email;

    private List<String> roles;


    public UserInfoResponse(Long idUser, String username, String email, List<String> roles) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

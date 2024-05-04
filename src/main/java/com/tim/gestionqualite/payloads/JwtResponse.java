package com.tim.gestionqualite.payloads;

import java.util.List;

public class JwtResponse {



    private String jwt ;

    private Long Id ;
    private String firstName ;

    private String email ;

    private List<String> roles;

    public JwtResponse(String jwt, Long id, String firstName, String email, List<String> roles) {
        this.jwt = jwt;
        Id = id;
        this.firstName = firstName;
        this.email = email;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String username) {
        this.firstName = username;
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

package com.example.OnlineJobPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
public class Users {

    @Id
    private int id;
    private String username;
    private String email;
    private String password;

//    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Roles> roles;

    public int getId() {
        return id;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

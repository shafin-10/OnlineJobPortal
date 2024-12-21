package com.example.OnlineJobPortal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "users")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Roles> rolesList;

    @JsonManagedReference
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Jobs> jobsList;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<JobApplication> jobApplicationList;

    public int getId() {
        return id;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
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

    public List<Jobs> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }

    public List<JobApplication> getJobApplicationList() {
        return jobApplicationList;
    }

    public void setJobApplicationList(List<JobApplication> jobApplicationList) {
        this.jobApplicationList = jobApplicationList;
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

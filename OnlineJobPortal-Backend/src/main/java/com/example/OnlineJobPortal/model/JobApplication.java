package com.example.OnlineJobPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "job_application")
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private Date applicationDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users users; //JOB SEEKER ID

    @ManyToOne
    @JoinColumn(name = "jobs_id")
    @JsonIgnore
    private Jobs jobs;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", AppliedDate=" + applicationDate +
                '}';
    }
}

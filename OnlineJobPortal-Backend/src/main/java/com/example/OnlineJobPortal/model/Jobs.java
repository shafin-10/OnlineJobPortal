package com.example.OnlineJobPortal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "jobs")
@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String companyName;

    private String jobDescription;

    private String skills;

    private String salaryRange;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonBackReference
    private Users users; //EMPLOYER ID

    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
    private List<JobApplication> jobApplicationList;

    public List<JobApplication> getJobApplicationList() {
        return jobApplicationList;
    }

    public void setJobApplicationList(List<JobApplication> jobApplicationList) {
        this.jobApplicationList = jobApplicationList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", skills='" + skills + '\'' +
                ", salaryRange='" + salaryRange + '\'' +
                '}';
    }
}

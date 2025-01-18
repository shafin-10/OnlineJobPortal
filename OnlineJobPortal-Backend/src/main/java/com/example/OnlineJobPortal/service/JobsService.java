package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.JobsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobsService {
    @Autowired
    JobsRepo jobsRepo;
    @Autowired
    AuthService authService;

    public Jobs addNewJob(Jobs jobs) {
        jobs.setCreatedDate(new Date());

        Users users = authService.getCurrentLoggedInUser();
        jobs.setUsers(users);

        return jobsRepo.save(jobs);
    }

    public Jobs getJobById(int id) {
        return jobsRepo.findById(id).orElse(null);
    }

    public List<Jobs> getAllJobs() {
        return jobsRepo.findAll();
    }

    public void deleteJob(int id) {
       jobsRepo.deleteById(id);
    }

    public Jobs updateJob(int id, Jobs job) {
        return jobsRepo.save(job);
    }

    public List<Jobs> getAllJobsByParams(String params) {
        return jobsRepo.findAllByParams(params);
    }
}

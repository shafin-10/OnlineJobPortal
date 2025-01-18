package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.JobApplication;
import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.JobApplicationRepo;
import com.example.OnlineJobPortal.repo.JobsRepo;
import com.example.OnlineJobPortal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobApplicationController {

    @Autowired
    JobApplicationRepo jobApplicationRepo;
    @Autowired
    JobsRepo jobsRepo;
    @Autowired
    AuthService authService;

    @PostMapping("/application/{id}")
    public ResponseEntity<Object> createJobApplication(@PathVariable int id) {
        try {
            Users users = authService.getCurrentLoggedInUser();
            Jobs jobs = jobsRepo.findById(id).get();

            if (!jobApplicationRepo.findByJobsAndUsers(jobs, users).isEmpty()) {
                return new ResponseEntity<>("User has already applied for this job application", HttpStatus.ALREADY_REPORTED);
            }

            JobApplication jobApplication = JobApplication.builder()
                    .jobs(jobs)
                    .status("ACTIVE")
                    .users(users)
                    .applicationDate(new Date())
                    .build();

            return new ResponseEntity<>(jobApplicationRepo.save(jobApplication), HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable int id) {
        JobApplication jobApplication = jobApplicationRepo.findById((long) id).get();
        if (jobApplication != null) {
            jobApplicationRepo.deleteById((long) id);
            return new ResponseEntity<>("Job Application successfully deleted.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Job Application not found.", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/applicatants/{jobId}")
    public ResponseEntity<List<Users>>  getApplicantsByJobId(@PathVariable int jobId){
        List<JobApplication> ja = jobApplicationRepo.findByJobsId(jobId);
        List<Users> users = new ArrayList<>();
        ja.forEach(jobApplication -> {
            Users user = jobApplication.getUsers();
            users.add(user);
        });
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

}
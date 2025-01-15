package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobSeekerController {

    @Autowired
    JobSeekerService jobSeekerService;

    @GetMapping("/jobseekers")
    public ResponseEntity<List<Users>> getjobseekers(){
        return new ResponseEntity<>(jobSeekerService.getJobSeeker(), HttpStatus.OK);
    }

    //applied job api
}

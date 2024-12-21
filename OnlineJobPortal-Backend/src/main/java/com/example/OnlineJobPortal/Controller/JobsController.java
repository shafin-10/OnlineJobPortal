package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobsController {
    @Autowired
    JobsService jobsService;

    @PostMapping("/addJob")
    public ResponseEntity addJobs(@RequestBody Jobs jobs) {
        Jobs newJob = jobsService.addNewJob(jobs);
        return ResponseEntity.ok(newJob);
    }

}

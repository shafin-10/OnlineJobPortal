package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobsController {
    @Autowired
    JobsService jobsService;

    @GetMapping("/jobs")
    public ResponseEntity<List<Jobs>> getAllJobs(){
        return new ResponseEntity<>(jobsService.getAllJobs(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity addJobs(@RequestBody Jobs jobs) {
        try {
            Jobs newJob = jobsService.addNewJob(jobs);
            return ResponseEntity.ok(newJob);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Jobs> getJobById(@PathVariable int id){
        Jobs jobs = jobsService.getJobById(id);
        if(jobs != null)
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobs(@PathVariable int id) {
        Jobs jobs = jobsService.getJobById(id);
        if (jobs != null) {
            jobsService.deleteJob(id);
            return new ResponseEntity<>("Job successfully deleted.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id, @RequestBody Jobs job){
        Jobs jobs1 = jobsService.updateJob(id, job);
        if (jobs1 != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed to update", HttpStatus.NOT_FOUND);
    }

    // add search job api


}

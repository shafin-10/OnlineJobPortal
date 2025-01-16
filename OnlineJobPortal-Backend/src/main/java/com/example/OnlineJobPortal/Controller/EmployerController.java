package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployerController {

    @Autowired
    EmployerService employerService;

    @GetMapping("/employers")
    public ResponseEntity<List<Users>> getEmployers(){
        return new ResponseEntity<>(employerService.getEmployers(), HttpStatus.OK);
    }

    //posted jobs api
    @GetMapping("/postedJobs/{id}")
    public ResponseEntity<List<Jobs>> getPostedJobs(@PathVariable int id){
        return new ResponseEntity<>(employerService.getPostedJobs(id), HttpStatus.OK);
    }

}

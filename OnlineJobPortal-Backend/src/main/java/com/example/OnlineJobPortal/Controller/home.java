package com.example.OnlineJobPortal.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {

    @GetMapping("/")
    public String greet(){
        return "hello";
    }

    @PostMapping("/addJob")
    public String addNewJob(){
        return "addNewJob";
    }

    @PostMapping("/applyJob")
    public String applyJob(){
        return "applyJob";
    }
}

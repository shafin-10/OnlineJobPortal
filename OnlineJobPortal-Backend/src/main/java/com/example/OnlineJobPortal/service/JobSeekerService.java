package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.model.Roles;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobSeekerService {

    @Autowired
    UserRepo userRepo;

    //get all Jobseekers
    public List<Users> getJobSeeker() {
        List<Users> allUser = userRepo.findAll();
        List<Users> jobSeekers = new ArrayList<>();

        for (Users i : allUser){
            for(Roles r : i.getRolesList()){
                if (r.getName().equals("ROLE_JOBSEEKER")) {
                    jobSeekers.add(i);
                }
            }
        }
        return jobSeekers;
    }


}

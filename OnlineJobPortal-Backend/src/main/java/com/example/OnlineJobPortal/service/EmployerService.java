package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Roles;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    UserRepo userRepo;

    //get all employers
    public List<Users> getEmployers() {
        List<Users> allUser = userRepo.findAll();
        List<Users> employers = new ArrayList<>();

        for (Users i : allUser){
            for(Roles r : i.getRolesList()){
                if (r.getName().equals("ROLE_EMPLOYEER")) {
                    employers.add(i);
                }
            }
        }
        return employers;
    }

    //get posted jobs
    public List<Jobs> getPostedJobs(int id) {
        return userRepo.findById(id).get().getJobsList();
    }
}

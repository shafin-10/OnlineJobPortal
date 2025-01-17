package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Users getUsersById(int id) {
        return userRepo.findById(id).orElse(null);
    }


    /*public Users getUsersByEmail(String email) {
        return userRepo.findByEmail(email);
    }*/


    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    public Users updateUser(int id, Users users) {
        return userRepo.save(users);
    }
}

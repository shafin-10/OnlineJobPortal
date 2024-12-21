package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.Security.JWTservice;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepo repo;

    @Autowired
    JWTservice jwTservice;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwTservice.generateToken(user.getEmail());
        else
            return "failure";
    }
}

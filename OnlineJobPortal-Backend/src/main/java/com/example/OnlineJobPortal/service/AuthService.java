package com.example.OnlineJobPortal.service;

import com.example.OnlineJobPortal.Security.JWTservice;
import com.example.OnlineJobPortal.model.Roles;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.record.RegisterRecord;
import com.example.OnlineJobPortal.repo.RolesRepo;
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
    UserRepo userRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    JWTservice jwTservice;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Object register(RegisterRecord data) {
        try {
            if (!data.roles().equals("EMPLOYEER") && !data.roles().equals("JOBSEEKER")) {
                return "Invalid User Role";
            }

            if (isUserAlreadyRegistered(data.email())) {
                return "User Already Registered";
            }

            Users user = new Users();
            user.setUsername(data.name());
            user.setEmail(data.email());
            user.setPassword(encoder.encode(data.password()));
            Users newUser = userRepo.save(user);

            Roles roles = new Roles();
            roles.setName("ROLE_" + data.roles());
            roles.setUsers(newUser);
            rolesRepo.save(roles);
            return "User Successfully Registered";
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
     }

     public boolean isUserAlreadyRegistered(String email) {
         return userRepo.findByEmail(email) != null ? true : false;
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

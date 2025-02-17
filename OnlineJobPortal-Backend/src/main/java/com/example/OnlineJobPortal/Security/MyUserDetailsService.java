package com.example.OnlineJobPortal.Security;

import com.example.OnlineJobPortal.model.Roles;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.repo.RolesRepo;
import com.example.OnlineJobPortal.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RolesRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
//        return new UserPrincipal(user);

        List<Roles> roles = user.getRolesList();

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Roles it : roles) {
            authorities.add(new SimpleGrantedAuthority(it.getName()));
        }

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
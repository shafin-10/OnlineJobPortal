package com.example.OnlineJobPortal.repo;

import com.example.OnlineJobPortal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    Users findByEmail(String username);
}


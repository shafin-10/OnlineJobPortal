package com.example.OnlineJobPortal.repo;

import com.example.OnlineJobPortal.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {
}

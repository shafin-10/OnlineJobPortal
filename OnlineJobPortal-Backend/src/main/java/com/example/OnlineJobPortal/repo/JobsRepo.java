package com.example.OnlineJobPortal.repo;

import com.example.OnlineJobPortal.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepo extends JpaRepository<Jobs, Integer> {
}

package com.example.OnlineJobPortal.repo;

import com.example.OnlineJobPortal.model.JobApplication;
import com.example.OnlineJobPortal.model.Jobs;
import com.example.OnlineJobPortal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    @Query("SELECT ja FROM JobApplication ja WHERE ja.jobs = :jobs AND ja.users = :users")
    List<JobApplication> findByJobsAndUsers(Jobs jobs, Users users);

    @Query("SELECT ja FROM JobApplication ja WHERE ja.jobs.id = :jobId")
    List<JobApplication> findByJobsId(int jobId);
}

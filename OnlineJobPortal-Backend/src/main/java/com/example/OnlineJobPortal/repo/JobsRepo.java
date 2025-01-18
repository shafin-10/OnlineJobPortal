package com.example.OnlineJobPortal.repo;

import com.example.OnlineJobPortal.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepo extends JpaRepository<Jobs, Integer> {
     @Query("SELECT j FROM Jobs j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :params, '%'))")
    List<Jobs> findAllByParams(String params);
}

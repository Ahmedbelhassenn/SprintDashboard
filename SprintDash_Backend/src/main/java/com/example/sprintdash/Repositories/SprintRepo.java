package com.example.sprintdash.Repositories;

import com.example.sprintdash.Models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SprintRepo extends JpaRepository<Sprint, Long> {
    Optional<Sprint> findBySprintId(String sprintId);
}


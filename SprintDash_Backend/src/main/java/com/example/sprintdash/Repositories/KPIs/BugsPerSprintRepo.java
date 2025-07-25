package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.BugsPerSprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface BugsPerSprintRepo extends JpaRepository<BugsPerSprint, Long> {
    Optional<BugsPerSprint> findBugsPerSprintBySprintId(String sprintId);
}

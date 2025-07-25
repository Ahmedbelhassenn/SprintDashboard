package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.TotalBugs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TotalBugsRepo extends JpaRepository<TotalBugs, Long> {
    Optional<TotalBugs> findByPeriod(String  period);
}

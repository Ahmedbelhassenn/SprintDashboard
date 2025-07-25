package com.example.sprintdash.Repositories.KPIs;

import com.example.sprintdash.Models.KPIs.SprintFailureRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface SprintFailureRateRepo extends JpaRepository<SprintFailureRate, Long> {
    Optional<SprintFailureRate> findByPeriod(String period);
}

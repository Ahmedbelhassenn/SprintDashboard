package com.example.sprintdash.Repositories.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VelocityAvgRepo extends JpaRepository<VelocityAvg, Long> {
    Optional<VelocityAvg> findByPeriod(String period);
}

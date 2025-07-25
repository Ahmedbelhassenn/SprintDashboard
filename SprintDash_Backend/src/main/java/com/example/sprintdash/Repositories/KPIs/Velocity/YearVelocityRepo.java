package com.example.sprintdash.Repositories.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.YearVelocity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface YearVelocityRepo extends JpaRepository<YearVelocity, Long> {
    Optional<YearVelocity> findByYear(String year);
}

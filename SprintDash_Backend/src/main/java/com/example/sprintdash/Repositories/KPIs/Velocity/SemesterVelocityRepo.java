package com.example.sprintdash.Repositories.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.SemesterVelocity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface SemesterVelocityRepo extends JpaRepository<SemesterVelocity, Long> {
    Optional<SemesterVelocity> findBySemester(String semester);
}

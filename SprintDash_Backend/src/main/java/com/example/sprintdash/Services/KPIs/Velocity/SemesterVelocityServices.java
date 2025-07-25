package com.example.sprintdash.Services.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.SemesterVelocity;

import java.util.List;
import java.util.Optional;

public interface SemesterVelocityServices {
    List<SemesterVelocity> getAllSemesterVelocities();
    Optional<SemesterVelocity> getSemesterVelocityBySemester(String semester);
    void calculateSemesterVelocity();

}

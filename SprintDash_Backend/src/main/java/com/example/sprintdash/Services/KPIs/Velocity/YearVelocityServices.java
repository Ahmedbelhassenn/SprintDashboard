package com.example.sprintdash.Services.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.YearVelocity;

import java.util.List;
import java.util.Optional;

public interface YearVelocityServices {
    List<YearVelocity> getAllYearVelocities();
    Optional<YearVelocity> getYearVelocityBySemester(String year);
    void calculateYearVelocity();
}

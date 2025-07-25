package com.example.sprintdash.Services.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.TrimesterVelocity;

import java.util.List;
import java.util.Optional;

public interface TrimesterVelocityServices {
    List<TrimesterVelocity> getAllTrimesterVelocities();
    Optional<TrimesterVelocity> getTrimesterVelocityByTrimester(String trimester);
    void calculateTrimesterVelocity();
}

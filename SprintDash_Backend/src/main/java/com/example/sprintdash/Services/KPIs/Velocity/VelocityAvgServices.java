package com.example.sprintdash.Services.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;

import java.util.List;
import java.util.Optional;

public interface VelocityAvgServices {
    List<VelocityAvg> getAllVelocitiesAvg();
    Optional<VelocityAvg> getVelocityAvgByPeriod(String period);
    void calculateVelocityAvg();
}

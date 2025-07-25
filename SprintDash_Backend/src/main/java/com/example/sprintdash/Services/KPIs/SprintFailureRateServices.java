package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.SprintFailureRate;

import java.util.List;
import java.util.Optional;


public interface SprintFailureRateServices {
    List<SprintFailureRate> getAllSprintFailureRate();
    Optional<SprintFailureRate> getSprintFailureRateByPeriod(String period);
    void calculateSprintFailureRate();

}

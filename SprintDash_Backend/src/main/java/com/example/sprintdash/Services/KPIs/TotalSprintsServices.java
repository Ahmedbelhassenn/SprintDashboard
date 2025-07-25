package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;

import java.util.List;
import java.util.Optional;

public interface TotalSprintsServices {
    List<TotalSprints> getAllTotalSprints();
    Optional<TotalSprints> getTotalSprintByPeriod(String period);
    void updateTotalSprint();
}

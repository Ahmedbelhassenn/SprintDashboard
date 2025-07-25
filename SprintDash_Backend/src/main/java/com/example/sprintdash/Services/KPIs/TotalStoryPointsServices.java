package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;
import com.example.sprintdash.Models.KPIs.TotalStoryPoints;

import java.util.List;
import java.util.Optional;

public interface TotalStoryPointsServices {
    List<TotalStoryPoints> getAllTotalStoryPoints();
    Optional<TotalStoryPoints> getTotalStoryPointsByPeriod(String period);
    void updateTotalStoryPoints();
}

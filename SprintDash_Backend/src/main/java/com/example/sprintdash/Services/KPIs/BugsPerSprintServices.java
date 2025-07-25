package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.BugsPerSprint;

import java.util.List;
import java.util.Optional;

public interface BugsPerSprintServices {
    List<BugsPerSprint> getAllBugsPerSprint();
    Optional<BugsPerSprint> getBugsPerSprintBySprintId(String sprintId);
    void updateBugsPerSprint();

}

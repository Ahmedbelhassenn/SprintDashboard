package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.TotalBugs;

import java.util.List;
import java.util.Optional;

public interface TotalBugsServices {
    List<TotalBugs> getAllTotalBugs();
    Optional<TotalBugs> getTotalBugs(String period);
    void updateTotalBugs();
}

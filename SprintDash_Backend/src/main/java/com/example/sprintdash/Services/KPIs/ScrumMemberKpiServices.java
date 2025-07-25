package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.ScrumMemberKpi;
import java.util.List;
import java.util.Optional;

public interface ScrumMemberKpiServices {
    List<ScrumMemberKpi> getAllScrumMembersKpi();
    Optional<ScrumMemberKpi> getScrumMemberKpiById(Long id);
    Optional<ScrumMemberKpi> getScrumMemberKpiByEmail(String email);
    void calculateMemberStoryPoints();
    void calculateMemberTasksToDo();
    void calculateMemberTasksCompleted();
}

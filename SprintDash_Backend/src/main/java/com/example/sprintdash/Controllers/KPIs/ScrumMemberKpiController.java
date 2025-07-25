package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.ScrumMemberKpi;
import com.example.sprintdash.Services.KPIs.ScrumMemberKpiServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member/kpi")
@CrossOrigin(origins = "http://localhost:4200")
public class ScrumMemberKpiController {
    @Autowired
    ScrumMemberKpiServices scrumMemberKpiServices;
    @GetMapping("/all")
    public List<ScrumMemberKpi> getAllScrumMembers() {
        return scrumMemberKpiServices.getAllScrumMembersKpi();
    }
    @GetMapping("{id}")
    public Optional<ScrumMemberKpi> getScrumMemberKpiById(@PathVariable Long id){
        return scrumMemberKpiServices.getScrumMemberKpiById(id);
    }

    @PutMapping("/storyPoints")
    public void calculateMemberStoryPoints(){
        scrumMemberKpiServices.calculateMemberStoryPoints();
    }
    @PutMapping("/tasksToDo")
    public void calculateMemberTasksToDo(){
        scrumMemberKpiServices.calculateMemberTasksToDo();
    }
    @PutMapping("/tasksCompleted")
    public void calculateMemberTasksCompleted(){
        scrumMemberKpiServices.calculateMemberTasksCompleted();
    }
}

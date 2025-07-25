package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.TotalSprintsRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.TotalSprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TotalSprintsServicesImpl implements TotalSprintsServices {
    @Autowired
    TotalSprintsRepo totalSprintsRepo;
    @Autowired
    SprintRepo sprintRepo;

    public List<TotalSprints> getAllTotalSprints(){
        return totalSprintsRepo.findAll();
    }
    public Optional<TotalSprints> getTotalSprintByPeriod(String period){
        return totalSprintsRepo.findByPeriod(period);
    }
    public void updateTotalSprint(){
        List<Sprint> totalSprints = sprintRepo.findAll();
        int counter = 0;
        for (Sprint sprint : totalSprints) {
            if("closed".equals(sprint.getState())) counter++;
        }
        TotalSprints totalSprint =totalSprintsRepo.findByPeriod("all").orElse(new TotalSprints());
        totalSprint.setPeriod("all");
        totalSprint.setTotalSprints((long) totalSprints.size());
        totalSprint.setTotalSprintsCompleted((long)counter);
        totalSprintsRepo.save(totalSprint);
    }
}

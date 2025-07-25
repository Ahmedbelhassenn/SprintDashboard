package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.TotalStoryPoints;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.TotalStoryPointsRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.TotalStoryPointsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TotalStoryPointsServicesImpl  implements TotalStoryPointsServices {
    @Autowired
    TotalStoryPointsRepo totalStoryPointsRepo;
    @Autowired
    SprintRepo sprintRepo;

    public List<TotalStoryPoints> getAllTotalStoryPoints(){
        return totalStoryPointsRepo.findAll();
    }
    public Optional<TotalStoryPoints> getTotalStoryPointsByPeriod(String period){
        return totalStoryPointsRepo.findByPeriod(period);
    }
    public void updateTotalStoryPoints(){
        List<Sprint> totalStoryPoints = sprintRepo.findAll();
        int estimated = 0, completed = 0;
        for (Sprint sprint : totalStoryPoints) {
            if (sprint.getCompletedStoryPoints() != null){
                completed+=sprint.getCompletedStoryPoints();
            }
            if (sprint.getEstimatedStoryPoints() != null){
                estimated+=sprint.getEstimatedStoryPoints();
            }
        }
        TotalStoryPoints totalStoryPoint =totalStoryPointsRepo.findByPeriod("all").orElse(new TotalStoryPoints());
        totalStoryPoint.setPeriod("all");
        totalStoryPoint.setTotalStoryPointsCompleted((long)completed);
        totalStoryPoint.setTotalStoryPoints((long)estimated);
        totalStoryPointsRepo.save(totalStoryPoint);
    }
}

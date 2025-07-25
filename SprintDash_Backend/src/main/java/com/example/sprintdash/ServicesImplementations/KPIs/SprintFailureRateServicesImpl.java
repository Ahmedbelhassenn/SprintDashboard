package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.SprintFailureRate;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.SprintFailureRateRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.SprintFailureRateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintFailureRateServicesImpl implements SprintFailureRateServices {
    @Autowired
    SprintFailureRateRepo sprintFailureRateRepo;
    @Autowired
    SprintRepo sprintRepo;

    public List<SprintFailureRate> getAllSprintFailureRate(){
        return sprintFailureRateRepo.findAll();
    }
    public Optional<SprintFailureRate> getSprintFailureRateByPeriod(String period){
        return sprintFailureRateRepo.findByPeriod(period);
    }

    public void calculateSprintFailureRate(){
        List<Sprint> sprints = sprintRepo.findAll();
        if (!sprints.isEmpty()) {
            String period="all";
            int counter1=0, counter2=0;
            for (Sprint sprint : sprints) {
                if(sprint.getCompletedStoryPoints() != null && "closed".equals(sprint.getState()) ){
                    counter1++;
                    if(sprint.getCompletedStoryPoints() < sprint.getEstimatedStoryPoints()){
                        counter2++;
                    }
                }
            }
            SprintFailureRate sprintFailureRate = sprintFailureRateRepo.findByPeriod(period)
                    .orElse(new SprintFailureRate());
            sprintFailureRate.setPeriod(period);
            sprintFailureRate.setFailureRate((double)counter2/counter1);
            sprintFailureRateRepo.save(sprintFailureRate);
        }
    }
}

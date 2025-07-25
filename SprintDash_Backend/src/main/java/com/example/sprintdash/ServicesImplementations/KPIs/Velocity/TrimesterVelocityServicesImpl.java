package com.example.sprintdash.ServicesImplementations.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.TrimesterVelocity;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.Velocity.TrimesterVelocityRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.Velocity.TrimesterVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TrimesterVelocityServicesImpl implements TrimesterVelocityServices {
    @Autowired
    TrimesterVelocityRepo trimesterVelocityRepo;
    @Autowired
    SprintRepo sprintRepo;

    @Override
    public List<TrimesterVelocity> getAllTrimesterVelocities() {
        return trimesterVelocityRepo.findAll();
    }

    @Override
    public Optional<TrimesterVelocity> getTrimesterVelocityByTrimester(String trimester) {
        return trimesterVelocityRepo.findByTrimester(trimester);
    }

    public void calculateTrimesterVelocity(){
        List<Sprint> sprints = sprintRepo.findAll();
        Map<String, Long> velocityPerTrimester = new HashMap<>();
        for (Sprint sprint : sprints) {
            String trimester;
            if(sprint.getStartDate()!= null && sprint.getEndDate() != null){
                if (sprint.getStartDate().substring(5, 7).matches("0[1-3]")) {
                    trimester=sprint.getStartDate().substring(0, 4)+" T1";
                } else if (sprint.getStartDate().substring(5, 7).matches("0[4-6]")) {
                    trimester=sprint.getStartDate().substring(0, 4)+" T2";
                } else if (sprint.getStartDate().substring(5, 7).matches("0[7-9]")) {
                    trimester=sprint.getStartDate().substring(0, 4)+" T3";
                } else {
                    trimester=sprint.getStartDate().substring(0, 4)+" T4";
                }
                if (sprint.getCompletedStoryPoints() != null) {
                    velocityPerTrimester.put(trimester, velocityPerTrimester.getOrDefault(trimester, 0L)
                            +sprint.getCompletedStoryPoints());
                }
            }
        }
        for(Map.Entry<String, Long> entry : velocityPerTrimester.entrySet()) {
            String trimester = entry.getKey();
            Long velocity = entry.getValue();
            TrimesterVelocity trimesterVelocity= trimesterVelocityRepo.findByTrimester(trimester)
                    .orElse(new TrimesterVelocity());
            trimesterVelocity.setTrimester(trimester);
            trimesterVelocity.setVelocity(velocity);
            trimesterVelocityRepo.save(trimesterVelocity);
        }
    }
}

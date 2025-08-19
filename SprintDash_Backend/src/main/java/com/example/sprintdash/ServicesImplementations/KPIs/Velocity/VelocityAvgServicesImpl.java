package com.example.sprintdash.ServicesImplementations.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.Velocity.VelocityAvgRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.Velocity.VelocityAvgServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VelocityAvgServicesImpl implements VelocityAvgServices {
    @Autowired
    VelocityAvgRepo velocityAvgRepo;
    @Autowired
    SprintRepo sprintRepo;

    @Override
    public List<VelocityAvg> getAllVelocitiesAvg() {
        return velocityAvgRepo.findAll();
    }

    @Override
    public Optional<VelocityAvg> getVelocityAvgByPeriod(String period) {
        return velocityAvgRepo.findByPeriod(period);
    }

    public void calculateVelocityAvg(){
        List<Sprint> sprints = sprintRepo.findAll();
        int completed=0, counter=0;
        for(Sprint sprint : sprints){
            if("closed".equals(sprint.getState())){
                if(sprint.getCompletedStoryPoints() != null){
                    completed+= sprint.getCompletedStoryPoints();
                }
                counter++;
            }
        }
        VelocityAvg v=velocityAvgRepo.findByPeriod("all").orElseThrow(
                () -> new RuntimeException("No Velocity Avg found")
        );
        v.setVelocityAvg((double) completed /counter);
        velocityAvgRepo.save(v);
    }

}

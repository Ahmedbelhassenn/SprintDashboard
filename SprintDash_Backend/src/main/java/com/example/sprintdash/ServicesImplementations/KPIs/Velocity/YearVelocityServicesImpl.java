package com.example.sprintdash.ServicesImplementations.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.YearVelocity;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.Velocity.YearVelocityRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.Velocity.YearVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class YearVelocityServicesImpl implements YearVelocityServices {
    @Autowired
    YearVelocityRepo yearVelocityRepo;
    @Autowired
    SprintRepo sprintRepo;

    @Override
    public List<YearVelocity> getAllYearVelocities() {
        return yearVelocityRepo.findAll();
    }

    @Override
    public Optional<YearVelocity> getYearVelocityBySemester(String year) {
        return yearVelocityRepo.findByYear(year);
    }

    public void calculateYearVelocity() {
        List<Sprint> sprints = sprintRepo.findAll();
        Map<String, Long> velocityPerYear = new HashMap<>();

        for (Sprint sprint : sprints) {
            if(sprint.getStartDate()!= null && sprint.getEndDate() != null) {
                String year = sprint.getStartDate().substring(0, 4);
                if (sprint.getCompletedStoryPoints() != null) {
                    velocityPerYear.put(year, velocityPerYear.getOrDefault(year, 0L)
                            + sprint.getCompletedStoryPoints());
                }
            }

        }
        for (Map.Entry<String, Long> entry : velocityPerYear.entrySet()) {
            String year = entry.getKey();
            Long velocity = entry.getValue();
            YearVelocity yearVelocity = yearVelocityRepo.findByYear(year)
                    .orElse(new YearVelocity());
            yearVelocity.setYear(year);
            yearVelocity.setVelocity(velocity);
            yearVelocityRepo.save(yearVelocity);
        }
    }

}

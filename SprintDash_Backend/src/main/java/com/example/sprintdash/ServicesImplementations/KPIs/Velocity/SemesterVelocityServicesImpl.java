package com.example.sprintdash.ServicesImplementations.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.SemesterVelocity;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.Velocity.SemesterVelocityRepo;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.KPIs.Velocity.SemesterVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SemesterVelocityServicesImpl implements SemesterVelocityServices {
    @Autowired
    SemesterVelocityRepo semesterVelocityRepo;
    @Autowired
    SprintRepo sprintRepo;

    public List<SemesterVelocity> getAllSemesterVelocities() {
        return semesterVelocityRepo.findAll();
    }

    @Override
    public Optional<SemesterVelocity> getSemesterVelocityBySemester(String semester) {
        return semesterVelocityRepo.findBySemester(semester);
    }

    public void calculateSemesterVelocity(){
        List<Sprint> sprints = sprintRepo.findAll();
        Map<String, Long> velocityPerSemester = new HashMap<>();
        for (Sprint sprint : sprints) {
            String semester;
            if (sprint.getStartDate()!= null && sprint.getEndDate()!= null) {
                if (sprint.getStartDate().substring(5, 7).matches("0[1-6]")) {
                    semester=sprint.getStartDate().substring(0, 4)+" H1";
                }
                else{
                    semester=sprint.getStartDate().substring(0, 4)+" H2";
                }
                if (sprint.getCompletedStoryPoints() != null) {
                    velocityPerSemester.put(semester, velocityPerSemester.getOrDefault(semester, 0L)
                            + sprint.getCompletedStoryPoints());
                }
            }
        }
        for(Map.Entry<String, Long> entry : velocityPerSemester.entrySet()) {
            String semester = entry.getKey();
            Long velocity = entry.getValue();
            SemesterVelocity semesterVelocity= semesterVelocityRepo.findBySemester(semester)
                    .orElse(new SemesterVelocity());
            semesterVelocity.setSemester(semester);
            semesterVelocity.setVelocity(velocity);
            semesterVelocityRepo.save(semesterVelocity);
        }
    }
}

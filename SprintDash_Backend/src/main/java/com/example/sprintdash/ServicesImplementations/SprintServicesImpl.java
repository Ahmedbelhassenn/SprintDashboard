package com.example.sprintdash.ServicesImplementations;

import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponse;
import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponseContainer;
import com.example.sprintdash.JiraApi.JiraClient;
import com.example.sprintdash.Models.KPIs.Velocity.SemesterVelocity;
import com.example.sprintdash.Models.KPIs.Velocity.TrimesterVelocity;
import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;
import com.example.sprintdash.Models.KPIs.Velocity.YearVelocity;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Repositories.KPIs.SprintFailureRateRepo;
import com.example.sprintdash.Repositories.KPIs.Velocity.*;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Services.SprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SprintServicesImpl implements SprintServices {
    private final SprintRepo sprintRepo;
    private final JiraClient jiraClient;
    public SprintServicesImpl(SprintRepo sprintRepo, JiraClient jiraClient) {
        this.sprintRepo = sprintRepo;
        this.jiraClient = jiraClient;
    }





    public List<Sprint> getAllSprints(){
        return sprintRepo.findAll();
    }


    public Sprint getSprint(String sprintId){
        return sprintRepo.findBySprintId(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found with id: " + sprintId));
    }


    public JiraSprintResponseContainer getSprintDataFromJira(){
        return jiraClient.getJiraSprints("2");
    }

    public void updateSprintData(){
        JiraSprintResponseContainer responseContainer =  getSprintDataFromJira();
        if(responseContainer != null){
            List<JiraSprintResponse> sprintResponses=responseContainer.getValues();
            if(sprintResponses != null){
                for(JiraSprintResponse sprintResponse : sprintResponses){
                    Sprint sprint;
                    String sprintId = sprintResponse.getSprintId();
                    Optional<Sprint> existingSprint = sprintRepo.findBySprintId(sprintId);

                    if(existingSprint.isPresent()){
                        sprint = existingSprint.get();
                    }
                    else {
                        sprint = new Sprint();
                        sprint.setSprintId(sprintResponse.getSprintId());
                    }
                    sprint.setName(sprintResponse.getName());
                    sprint.setStartDate(sprintResponse.getStartDate());
                    sprint.setEndDate(sprintResponse.getEndDate());
                    sprint.setState(sprintResponse.getState());
                    sprint.setOriginBoardId(sprintResponse.getBoardId());
                    sprintRepo.save(sprint);
                }
            }
        }
    }


}

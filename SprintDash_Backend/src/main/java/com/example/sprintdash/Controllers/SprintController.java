package com.example.sprintdash.Controllers;


import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponseContainer;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Services.SprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sprint")
public class SprintController {
    @Autowired
    SprintServices sprintServices;

    @GetMapping("/all")
    public List<Sprint> getAllSprints(){
        return sprintServices.getAllSprints();
    }
    @GetMapping("/{id}")
    public Sprint getSprint(@PathVariable String id){
        return sprintServices.getSprint(id);
    }
    @GetMapping("/jira")
    public JiraSprintResponseContainer getSprintDataFromJira(){return sprintServices.getSprintDataFromJira();}
    @PutMapping("/update")
    public void updateSprintData(){
        sprintServices.updateSprintData();
    }




}

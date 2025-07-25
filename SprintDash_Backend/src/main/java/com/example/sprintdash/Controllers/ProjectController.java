package com.example.sprintdash.Controllers;

import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponseContainer;
import com.example.sprintdash.Models.Project;
import com.example.sprintdash.Services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    @Autowired
    ProjectServices projectServices;

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectServices.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable String id) {
        return projectServices.getProject(id);
    }

    @GetMapping("/jira")
    public JiraProjectResponseContainer getProjectDataFromJira(){
        return projectServices.getProjectDataFromJira();
    }

    @PutMapping("/update")
    public void updateProject() {
        projectServices.updateProject();
    }
}

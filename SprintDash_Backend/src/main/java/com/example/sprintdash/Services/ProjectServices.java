package com.example.sprintdash.Services;

import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponseContainer;
import com.example.sprintdash.Models.Project;


import java.util.List;

public interface ProjectServices {
    List<Project> getAllProjects();
    Project getProject(String projectId);
    JiraProjectResponseContainer getProjectDataFromJira();
    void updateProject();
}

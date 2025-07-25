package com.example.sprintdash.ServicesImplementations;

import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponse;
import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponseContainer;
import com.example.sprintdash.JiraApi.JiraClient;
import com.example.sprintdash.Models.Project;
import com.example.sprintdash.Repositories.ProjectRepo;
import com.example.sprintdash.Services.ProjectServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServicesImpl implements ProjectServices {

    private final ProjectRepo projectRepo;
    private final JiraClient jiraClient;

    public ProjectServicesImpl(ProjectRepo projectRepo, JiraClient jiraClient) {
        this.projectRepo = projectRepo;
        this.jiraClient = jiraClient;
    }

    public List<Project> getAllProjects(){
        return projectRepo.findAll();
    }

    public Project getProject(String projectId) {
        return projectRepo.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
    }

    public JiraProjectResponseContainer getProjectDataFromJira(){
        return jiraClient.getJiraProjects("1");
    }
    public void updateProject(){
        JiraProjectResponseContainer projectData = getProjectDataFromJira();
        if(projectData != null){
            List<JiraProjectResponse> jiraProjectResponses = projectData.getValues();
            for(JiraProjectResponse jiraProjectResponse : jiraProjectResponses){
                Optional<Project> existingProject = projectRepo.findByProjectId(jiraProjectResponse.getProjectId());
                Project project;
                if(existingProject.isPresent()){
                    project = existingProject.get();
                }
                else {
                    project = new Project();
                    project.setProjectId(jiraProjectResponse.getProjectId());
                }
                project.setName(jiraProjectResponse.getName());
                project.setProjectTypeKey(jiraProjectResponse.getProjectTypeKey());
                projectRepo.save(project);
            }
        }

    }
}

package com.example.sprintdash.Services;

import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponseContainer;
import com.example.sprintdash.Models.Sprint;

import java.util.List;

public interface SprintServices {
    List<Sprint> getAllSprints();
    Sprint getSprint(String sprintId);
    JiraSprintResponseContainer getSprintDataFromJira();
    void updateSprintData();
}

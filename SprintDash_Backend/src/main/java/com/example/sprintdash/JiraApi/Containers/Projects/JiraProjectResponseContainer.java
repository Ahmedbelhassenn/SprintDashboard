package com.example.sprintdash.JiraApi.Containers.Projects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiraProjectResponseContainer {
    private int maxResults;
    private int startAt;
    private int total;
    private boolean isLast;
    private List<JiraProjectResponse> values;
}

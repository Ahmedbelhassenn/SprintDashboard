package com.example.sprintdash.JiraApi.Containers.Projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraProjectResponse {
    @JsonProperty("id")
    private String projectId;
    private String projectTypeKey;
    private String name;

}

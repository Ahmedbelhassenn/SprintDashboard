package com.example.sprintdash.JiraApi.Containers.Sprint;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JiraSprintResponseContainer {
    private int maxResults;
    private int startAt;
    private int total;
    private boolean isLast;
    private List<JiraSprintResponse> values;
}

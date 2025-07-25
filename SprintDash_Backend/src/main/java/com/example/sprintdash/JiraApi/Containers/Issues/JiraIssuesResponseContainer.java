package com.example.sprintdash.JiraApi.Containers.Issues;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JiraIssuesResponseContainer {
    private int maxResults;
    private int startAt;
    private int total;
    private String expand;
    private List<JiraIssuesResponse> issues;
}

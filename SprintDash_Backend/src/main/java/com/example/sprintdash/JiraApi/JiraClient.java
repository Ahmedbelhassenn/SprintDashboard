package com.example.sprintdash.JiraApi;


import com.example.sprintdash.JiraApi.Containers.Issues.JiraIssuesResponseContainer;
import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponseContainer;
import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponseContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "jira-client", url = "${jira.base-url}", configuration = FeignConfig.class)
@Service
public interface JiraClient {
    @GetMapping("/rest/agile/1.0/board/{board-id}/sprint")
    JiraSprintResponseContainer getJiraSprints(@PathVariable("board-id") String boardId);
    @GetMapping("/rest/agile/1.0/board/{board-id}/project")
    JiraProjectResponseContainer getJiraProjects(@PathVariable("board-id") String boardId);
    @GetMapping("/rest/agile/1.0/board/{board-id}/issue")
    JiraIssuesResponseContainer getJiraIssues(@PathVariable("board-id") String boardId);
}
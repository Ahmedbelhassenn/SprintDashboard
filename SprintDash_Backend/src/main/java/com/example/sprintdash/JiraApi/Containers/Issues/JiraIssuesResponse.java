package com.example.sprintdash.JiraApi.Containers.Issues;


import com.example.sprintdash.JiraApi.Containers.Projects.JiraProjectResponse;
import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraIssuesResponse {
    @JsonProperty("id")
    private String ticketId;
    private String Key;
    private JiraFieldsResponse fields;

    @Setter
    @Getter
    public static class JiraFieldsResponse {
        @JsonProperty("issuetype")
        private Type type;
        private JiraSprintResponse sprint;
        private JiraProjectResponse project;
        @JsonProperty("priority")
        private IssuePriority issuePriority;
        @JsonProperty("customfield_10016")
        private Long storyPoints; // Estimated story points
        @JsonProperty("status")
        private IssueStatus issueStatus;
        @JsonProperty("assignee")
        private IssueAssignee issueAssignee;
        @Getter
        @Setter
        public static class Type {
            private String name;
        }
        @Getter
        @Setter
        public static class IssuePriority {
            private String name;
        }
        @Getter
        @Setter
        public static class IssueAssignee {
            @JsonProperty("displayName")
            private String name;
        }
        @Getter
        @Setter
        public static class IssueStatus {
            private String name;
        }

    }
}
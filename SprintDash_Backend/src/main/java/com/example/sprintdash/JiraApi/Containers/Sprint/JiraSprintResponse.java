package com.example.sprintdash.JiraApi.Containers.Sprint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JiraSprintResponse {
    @JsonProperty("id")
    private String sprintId;
    private String state;
    private String name;
    private String startDate;
    private String endDate;
    private String completeDate;
    @JsonProperty("originBoardId")
    private Long BoardId;
}

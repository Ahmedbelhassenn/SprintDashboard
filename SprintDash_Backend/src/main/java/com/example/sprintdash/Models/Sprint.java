package com.example.sprintdash.Models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sprintId;
    private String name;
    private String startDate;
    private String endDate;
    private String state;
    private Long originBoardId;
    private Long completedStoryPoints;
    private Long estimatedStoryPoints;
}

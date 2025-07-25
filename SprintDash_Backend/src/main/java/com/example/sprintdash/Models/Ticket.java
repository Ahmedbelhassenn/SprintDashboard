package com.example.sprintdash.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketId;
    private String nameKey;
    private String ticketType;
    private String issuePriority;
    private String issueState;
    private String assignedMember;
    private Long storyPoints;
    private String sprintId;
    private String projectId;
}

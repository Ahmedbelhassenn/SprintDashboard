package com.example.sprintdash.Models.KPIs;

import com.example.sprintdash.Models.ScrumMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class ScrumMemberKpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "scrumMemberId", referencedColumnName = "id", nullable = false)
    private ScrumMember scrumMember;
    private Long totalStoryPoints;
    private int tasksToDo;
    private int tasksCompleted;
}

package com.example.sprintdash.ServicesImplementations;

import com.example.sprintdash.Services.KPIs.*;
import com.example.sprintdash.Services.KPIs.Velocity.SemesterVelocityServices;
import com.example.sprintdash.Services.KPIs.Velocity.TrimesterVelocityServices;
import com.example.sprintdash.Services.KPIs.Velocity.VelocityAvgServices;
import com.example.sprintdash.Services.KPIs.Velocity.YearVelocityServices;
import com.example.sprintdash.Services.SprintServices;
import com.example.sprintdash.Services.TicketServices;
import com.example.sprintdash.Services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    TicketServices ticketServices;
    @Autowired
    SprintServices sprintServices;
    @Autowired
    SemesterVelocityServices semesterVelocityServices;
    @Autowired
    TrimesterVelocityServices trimesterVelocityServices;
    @Autowired
    VelocityAvgServices velocityAvgServices;
    @Autowired
    YearVelocityServices yearVelocityServices;
    @Autowired
    ScrumMemberKpiServices scrumMemberKpiServices;
    @Autowired
    SprintFailureRateServices sprintFailureRateServices;
    @Autowired
    TotalBugsServices totalBugsServices;
    @Autowired
    TotalSprintsServices totalSprintsServices;
    @Autowired
    TotalTicketsServices totalTicketsServices;
    @Autowired
    TotalStoryPointsServices totalStoryPointsServices;
    public void updateJira() {
        ticketServices.updateTicket();
        sprintServices.updateSprintData();
        ticketServices.calculateStoryPointsCompleted();
        ticketServices.calculateStoryPointsEstimated();
    }
    public  void updateKpis(){
        semesterVelocityServices.calculateSemesterVelocity();
        trimesterVelocityServices.calculateTrimesterVelocity();
        yearVelocityServices.calculateYearVelocity();
        sprintFailureRateServices.calculateSprintFailureRate();
        totalBugsServices.updateTotalBugs();
        totalSprintsServices.updateTotalSprint();
        totalTicketsServices.updateTotalTickets();
        totalStoryPointsServices.updateTotalStoryPoints();
    }
}

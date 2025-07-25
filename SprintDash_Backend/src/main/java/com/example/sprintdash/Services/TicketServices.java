package com.example.sprintdash.Services;

import com.example.sprintdash.JiraApi.Containers.Issues.JiraIssuesResponseContainer;
import com.example.sprintdash.Models.Ticket;

import java.util.List;

public interface TicketServices {
    Ticket getTicketByTicketId(String id);
    List<Ticket> getAllTickets();
    JiraIssuesResponseContainer getIssuesDataFromJira();
    void updateTicket();
    void calculateStoryPointsCompleted();
    void calculateStoryPointsEstimated();



}

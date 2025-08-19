package com.example.sprintdash.ServicesImplementations;

import com.example.sprintdash.JiraApi.Containers.Issues.JiraIssuesResponse;
import com.example.sprintdash.JiraApi.Containers.Issues.JiraIssuesResponseContainer;
import com.example.sprintdash.JiraApi.Containers.Sprint.JiraSprintResponse;
import com.example.sprintdash.JiraApi.JiraClient;
import com.example.sprintdash.Models.Sprint;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.SprintRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.SprintServices;
import com.example.sprintdash.Services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServicesImpl implements TicketServices {
    @Autowired
    SprintServices sprintServices;
    private final TicketRepo ticketRepo;
    private final JiraClient jiraClient;
    private final SprintRepo sprintRepo;


    public TicketServicesImpl(TicketRepo ticketRepo, JiraClient jiraClient, SprintRepo sprintRepo) {
        this.ticketRepo = ticketRepo;
        this.jiraClient = jiraClient;
        this.sprintRepo = sprintRepo;
    }

    public Ticket getTicketByTicketId(String ticketId){
        return ticketRepo.findByTicketId(ticketId).
                orElseThrow(() -> new RuntimeException("Ticket Not Found with id " + ticketId));
    }
    public List<Ticket> getAllTickets(){
        return ticketRepo.findAll();
    }

    public JiraIssuesResponseContainer getIssuesDataFromJira(){
        return jiraClient.getJiraIssues("2");
    }

    public void updateTicket(){
        JiraIssuesResponseContainer issues = getIssuesDataFromJira();
        if(issues != null){
            List<JiraIssuesResponse> issuesResponses = issues.getIssues();
            if(issuesResponses != null){
                for(JiraIssuesResponse issue: issuesResponses){
                    Optional<Ticket> existingTicket = ticketRepo.findByTicketId(issue.getTicketId());
                    Ticket ticket ;
                    if(existingTicket.isPresent()){
                        ticket = existingTicket.get();
                        ticket.setNameKey(issue.getKey());
                        ticket.setTicketType(issue.getFields().getType().getName());
                        ticket.setIssuePriority(issue.getFields().getIssuePriority().getName());
                        ticket.setIssueState(issue.getFields().getIssueStatus().getName());
                        JiraSprintResponse sprint = issue.getFields().getSprint();
                        if(sprint != null){
                            ticket.setSprintId(sprint.getSprintId());
                        }
                        String assignedMember = issue.getFields().getIssueAssignee() != null
                                ? issue.getFields().getIssueAssignee().getName() : "No Assigned";
                        Long storyPoints = issue.getFields().getStoryPoints() !=null
                                ? issue.getFields().getStoryPoints() : 0;
                        ticket.setProjectId(issue.getFields().getProject().getProjectId());
                        ticket.setAssignedMember(assignedMember);
                        ticket.setStoryPoints(storyPoints);
                    }
                    else{
                        ticket = new Ticket();
                        ticket.setTicketId(issue.getTicketId());
                        ticket.setNameKey(issue.getKey());
                        ticket.setTicketType(issue.getFields().getType().getName());
                        ticket.setIssuePriority(issue.getFields().getIssuePriority().getName());
                        ticket.setIssueState(issue.getFields().getIssueStatus().getName());
                        JiraSprintResponse sprint = issue.getFields().getSprint();
                        if(sprint != null){
                            ticket.setSprintId(sprint.getSprintId());
                        }
                        String assignedMember = issue.getFields().getIssueAssignee() != null
                                ? issue.getFields().getIssueAssignee().getName() : "No Assigned";
                        Long storyPoints = issue.getFields().getStoryPoints() !=null
                                ? issue.getFields().getStoryPoints() : 0;
                        ticket.setProjectId(issue.getFields().getProject().getProjectId());
                        ticket.setAssignedMember(assignedMember);
                        ticket.setStoryPoints(storyPoints);
                    }
                    ticketRepo.save(ticket);

                }
            }
        }
    }
    public void calculateStoryPointsCompleted(){
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            Map<String, Long> storyPointsMap = new HashMap<>();
            for (Ticket ticket : tickets) {
                if (ticket.getSprintId() != null) {
                   if("Terminé(e)".equals(ticket.getIssueState())){
                       storyPointsMap.put(ticket.getSprintId(),
                               storyPointsMap.getOrDefault(ticket.getSprintId(), 0L) + ticket.getStoryPoints());
                   }
                }
            }
            for(String key: storyPointsMap.keySet()){
                Sprint sprint= sprintServices.getSprint(key);
                if(sprint != null){
                    sprint.setCompletedStoryPoints(storyPointsMap.get(key));
                    sprintRepo.save(sprint);
                }

            }

        }
    }
    public void calculateStoryPointsEstimated(){
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            Map<String, Long> storyPointsMap = new HashMap<>();
            for (Ticket ticket : tickets) {
                if (ticket.getSprintId() != null) {
                    storyPointsMap.put(ticket.getSprintId(),
                            storyPointsMap.getOrDefault(ticket.getSprintId(), 0L) + ticket.getStoryPoints());
                }
            }
            for(String key: storyPointsMap.keySet()){
                Sprint sprint= sprintServices.getSprint(key);
                if(sprint != null){
                    sprint.setEstimatedStoryPoints(storyPointsMap.get(key));
                    sprintRepo.save(sprint);
                }

            }

        }
    }
}

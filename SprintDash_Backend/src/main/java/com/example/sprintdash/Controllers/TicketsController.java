package com.example.sprintdash.Controllers;

import com.example.sprintdash.JiraApi.Containers.Issues.JiraIssuesResponseContainer;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Services.TicketServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketsController {
    final
    TicketServices ticketServices;

    public TicketsController(TicketServices ticketServices) {
        this.ticketServices = ticketServices;
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketServices.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable String id) {
        return ticketServices.getTicketByTicketId(id);
    }

    @GetMapping("/jira")
    public JiraIssuesResponseContainer getIssuesDataFromJira(){
        return ticketServices.getIssuesDataFromJira();
    }

    @PutMapping("/update")
    public void updateTicket() {
        ticketServices.updateTicket();
    }

    @PutMapping("/estimated")
    public void calculateStoryPointsEstimated(){
        ticketServices.calculateStoryPointsEstimated();
    }

    @PutMapping("/completed")
    public void calculateStoryPointsCompleted(){
        ticketServices.calculateStoryPointsCompleted();
    }
}

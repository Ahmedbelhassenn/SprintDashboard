package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.TotalTickets;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.KPIs.TotalTicketsRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.KPIs.TotalTicketsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TotalTicketsServicesImpl implements TotalTicketsServices {
    @Autowired
    TotalTicketsRepo totalTicketsRepo;
    @Autowired
    TicketRepo ticketRepo;

    public List<TotalTickets> getAllTotalTickets(){
        return totalTicketsRepo.findAll();
    }
    public Optional<TotalTickets> getTotalTicketsByPeriod(String period){
        return totalTicketsRepo.findByPeriod(period);
    }
    public void updateTotalTickets(){
        List<Ticket> totalTickets = ticketRepo.findAll();
        int counter = 0;
        for (Ticket ticket : totalTickets) {
            if("Terminé(e)".equals(ticket.getIssueState())) counter++;
        }
        TotalTickets totalTicket =totalTicketsRepo.findByPeriod("all").orElse(new TotalTickets());
        totalTicket.setPeriod("all");
        totalTicket.setTotalTickets((long) totalTickets.size());
        totalTicket.setTotalTicketsCompleted((long)counter);
        totalTicketsRepo.save(totalTicket);
    }
}

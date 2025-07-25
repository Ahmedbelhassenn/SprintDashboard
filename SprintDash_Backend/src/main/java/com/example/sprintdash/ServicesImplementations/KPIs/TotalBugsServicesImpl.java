package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.TotalBugs;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.KPIs.TotalBugsRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.KPIs.TotalBugsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TotalBugsServicesImpl implements TotalBugsServices {
    @Autowired
    TotalBugsRepo totalBugsRepo;
    @Autowired
    TicketRepo ticketRepo;
    public List<TotalBugs> getAllTotalBugs(){
        return totalBugsRepo.findAll();
    }
    public Optional<TotalBugs> getTotalBugs(String period){
        return totalBugsRepo.findByPeriod(period);
    }

    public void updateTotalBugs(){
        List<Ticket> tickets = ticketRepo.findAll();
        Long counter1=0L,counter2=0L;
        if(!tickets.isEmpty()){
            for(Ticket ticket : tickets){
                if("Bug".equals(ticket.getTicketType()) ){
                    counter1++;
                    if("Terminé(e)".equals(ticket.getIssueState())){
                        counter2++;
                    }
                }
            }
            TotalBugs totalBugs = totalBugsRepo.findByPeriod("all").orElse(new TotalBugs());
            totalBugs.setPeriod("all");
            totalBugs.setTotalBugs(counter1);
            totalBugs.setBugsResolved(counter2);
            totalBugs.setBugsOpened(counter1-counter2);
            totalBugsRepo.save(totalBugs);

        }
    }
}

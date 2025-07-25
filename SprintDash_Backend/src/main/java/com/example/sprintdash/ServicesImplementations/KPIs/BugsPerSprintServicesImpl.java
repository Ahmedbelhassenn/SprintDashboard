package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.BugsPerSprint;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.KPIs.BugsPerSprintRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.KPIs.BugsPerSprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BugsPerSprintServicesImpl implements BugsPerSprintServices {
    @Autowired
    BugsPerSprintRepo bugsPerSprintRepo;
    @Autowired
    TicketRepo ticketRepo;
    public List<BugsPerSprint> getAllBugsPerSprint(){
        return bugsPerSprintRepo.findAll();
    }
    public Optional<BugsPerSprint> getBugsPerSprintBySprintId(String sprintId){
        return bugsPerSprintRepo.findBugsPerSprintBySprintId(sprintId);
    }
    public void updateBugsPerSprint(){
        List<Ticket> tickets = ticketRepo.findAll();
        List<Ticket> bugs = tickets.stream()
                .filter(ticket -> "Bug".equalsIgnoreCase(ticket.getTicketType()))
                .collect(Collectors.toList());
        Map<String, List<Ticket>> bugsBySprint = bugs.stream()
                .collect(Collectors.groupingBy(Ticket::getSprintId));
        bugsBySprint.forEach((sprintId, bugList) -> {
            if (sprintId != null) {
                long totalBugs = bugList.size();
                long bugsResolved = bugList.stream()
                        .filter(bug -> "Terminé(e)".equalsIgnoreCase(bug.getIssueState()))
                        .count();
                long bugsOpened = totalBugs - bugsResolved;

                // Vérifier si une entrée existe déjà pour ce sprint
                Optional<BugsPerSprint> existingEntry = bugsPerSprintRepo.findBugsPerSprintBySprintId(sprintId);

                if (!existingEntry.isPresent()) {
                    BugsPerSprint newEntry = new BugsPerSprint();
                    newEntry.setSprintId(sprintId);
                    newEntry.setTotalBugs(totalBugs);
                    newEntry.setBugsResolved(bugsResolved);
                    newEntry.setBugsOpened(bugsOpened);
                    bugsPerSprintRepo.save(newEntry);
                } else {
                    existingEntry.get().setTotalBugs(totalBugs);
                    existingEntry.get().setBugsResolved(bugsResolved);
                    existingEntry.get().setBugsOpened(bugsOpened);
                    bugsPerSprintRepo.save(existingEntry.get());
                }
            }
        });

    }
}

package com.example.sprintdash.ServicesImplementations.KPIs;

import com.example.sprintdash.Models.KPIs.ScrumMemberKpi;
import com.example.sprintdash.Models.ScrumMember;
import com.example.sprintdash.Models.Ticket;
import com.example.sprintdash.Repositories.KPIs.ScrumMemberKpiRepo;
import com.example.sprintdash.Repositories.ScrumMemberRepo;
import com.example.sprintdash.Repositories.TicketRepo;
import com.example.sprintdash.Services.KPIs.ScrumMemberKpiServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScrumMemberKpiServicesIimpl implements ScrumMemberKpiServices {
    @Autowired
    ScrumMemberKpiRepo scrumMemberKpiRepo;
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    ScrumMemberRepo scrumMemberRepo;
    @Override
    public List<ScrumMemberKpi> getAllScrumMembersKpi() {
        return scrumMemberKpiRepo.findAll();
    }

    public Optional<ScrumMemberKpi> getScrumMemberKpiById(Long id) {
        return scrumMemberKpiRepo.findById(id);
    }

    public Optional<ScrumMemberKpi> getScrumMemberKpiByEmail(String email){
        return Optional.ofNullable(scrumMemberKpiRepo.findByScrumMemberEmail(email));
    }

    @Override
    public void calculateMemberStoryPoints() {
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            Map<String, Long> storyPointsMap = new HashMap<>();
            for (Ticket ticket : tickets) {
                if (ticket.getAssignedMember() != null) {
                    if("Terminé(e)".equals(ticket.getIssueState())){
                        storyPointsMap.put(ticket.getAssignedMember(),
                                storyPointsMap.getOrDefault(ticket.getAssignedMember(), 0L) + ticket.getStoryPoints());
                    }
                }
            }
            for(String key: storyPointsMap.keySet()){
                ScrumMemberKpi scrumMemberKpi=scrumMemberKpiRepo.findByScrumMemberEmail(key);
                if(scrumMemberKpi == null){
                    scrumMemberKpi = new ScrumMemberKpi();
                }
                scrumMemberKpi.setTotalStoryPoints(storyPointsMap.get(key));
                ScrumMember scrumMember = scrumMemberRepo.findByEmail(key)
                        .orElseThrow(() -> new EntityNotFoundException("ScrumMember not found"));
                scrumMemberKpi.setScrumMember(scrumMember);
                scrumMemberKpiRepo.save(scrumMemberKpi);
            }
        }
    }

    public void calculateMemberTasksToDo(){
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            Map<String, Long> tasksToDo = new HashMap<>();
            for (Ticket ticket : tickets) {
                if (ticket.getAssignedMember() != null) {
                    if(!("Terminé(e)".equals(ticket.getIssueState()))){
                        tasksToDo.put(ticket.getAssignedMember(), tasksToDo
                                .getOrDefault(ticket.getAssignedMember(), 0L) + 1);
                    }
                }
            }
            for(String key: tasksToDo.keySet()){
                ScrumMemberKpi scrumMemberKpi=scrumMemberKpiRepo.findByScrumMemberEmail(key);
                if(scrumMemberKpi == null){
                    scrumMemberKpi = new ScrumMemberKpi();
                }
                scrumMemberKpi.setTasksToDo(Math.toIntExact(tasksToDo.get(key)));
                ScrumMember scrumMember = scrumMemberRepo.findByEmail(key)
                        .orElseThrow(() -> new EntityNotFoundException("ScrumMember not found"));
                scrumMemberKpi.setScrumMember(scrumMember);
                scrumMemberKpiRepo.save(scrumMemberKpi);
            }
        }
    }
    public void calculateMemberTasksCompleted(){
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            Map<String, Long> tasksCompleted = new HashMap<>();
            for (Ticket ticket : tickets) {
                if (ticket.getAssignedMember() != null) {
                    if("Terminé(e)".equals(ticket.getIssueState())){
                        tasksCompleted.put(ticket.getAssignedMember(), tasksCompleted
                                .getOrDefault(ticket.getAssignedMember(), 0L) + 1);
                    }
                }
            }
            for(String key: tasksCompleted.keySet()){
                ScrumMemberKpi scrumMemberKpi=scrumMemberKpiRepo.findByScrumMemberEmail(key);
                if(scrumMemberKpi == null){
                    scrumMemberKpi = new ScrumMemberKpi();
                }
                scrumMemberKpi.setTasksCompleted(Math.toIntExact(tasksCompleted.get(key)));
                ScrumMember scrumMember = scrumMemberRepo.findByEmail(key)
                        .orElseThrow(() -> new EntityNotFoundException("ScrumMember not found"));
                scrumMemberKpi.setScrumMember(scrumMember);
                scrumMemberKpiRepo.save(scrumMemberKpi);
            }
        }

    }
}

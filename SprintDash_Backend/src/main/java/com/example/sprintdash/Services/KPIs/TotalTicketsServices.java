package com.example.sprintdash.Services.KPIs;

import com.example.sprintdash.Models.KPIs.TotalTickets;

import java.util.List;
import java.util.Optional;

public interface TotalTicketsServices {
    List<TotalTickets> getAllTotalTickets();
    Optional<TotalTickets> getTotalTicketsByPeriod(String period);
    void updateTotalTickets();
}

package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.TotalTickets;
import com.example.sprintdash.Services.KPIs.TotalTicketsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/totalTickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TotalTicketsController {
    @Autowired
    TotalTicketsServices totalTicketsServices;

    @GetMapping("/all")
    public List<TotalTickets> getAllTotalTickets() {
        return totalTicketsServices.getAllTotalTickets();
    }

    @GetMapping("/{period}")
    public Optional<TotalTickets> getTotalTicketsByPeriod(@PathVariable String period) {
        return totalTicketsServices.getTotalTicketsByPeriod(period);
    }

    @PutMapping("/update")
    public void updateTotalTickets() {
        totalTicketsServices.updateTotalTickets();
    }
}

package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.TotalStoryPoints;
import com.example.sprintdash.Services.KPIs.TotalStoryPointsServices;
import com.example.sprintdash.ServicesImplementations.KPIs.TotalStoryPointsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/totalStoryPoints")
@CrossOrigin(origins = "http://localhost:4200")
public class TotalStoryPointsController {
    @Autowired
    TotalStoryPointsServicesImpl totalStoryPointsServices;

    @GetMapping("/all")
    public List<TotalStoryPoints> getAllTotalStoryPoints() {
        return totalStoryPointsServices.getAllTotalStoryPoints();
    }

    @GetMapping("/{period}")
    public Optional<TotalStoryPoints> getTotalSprintByPeriod(@PathVariable String period) {
        return totalStoryPointsServices.getTotalStoryPointsByPeriod(period);
    }

    @PutMapping("/update")
    public void updateTotalSprint() {
        totalStoryPointsServices.updateTotalStoryPoints();
    }
}

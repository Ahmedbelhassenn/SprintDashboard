package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.TotalSprints;
import com.example.sprintdash.Services.KPIs.TotalSprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/totalSprints")
@CrossOrigin(origins = "http://localhost:4200")
public class TotalSprintsController {
    @Autowired
    TotalSprintsServices totalSprintsServices;

    @GetMapping("/all")
    public List<TotalSprints> getAllTotalSprints() {
        return totalSprintsServices.getAllTotalSprints();
    }

    @GetMapping("/{period}")
    public Optional<TotalSprints> getTotalSprintByPeriod(@PathVariable String period) {
        return totalSprintsServices.getTotalSprintByPeriod(period);
    }

    @PutMapping("/update")
    public void updateTotalSprint() {
        totalSprintsServices.updateTotalSprint();
    }
}

package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.SprintFailureRate;
import com.example.sprintdash.Services.KPIs.SprintFailureRateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sprint/failureRate")
@CrossOrigin(origins = "http://localhost:4200")
public class SprintFailureRateController {
    @Autowired
    SprintFailureRateServices sprintFailureRateServices;

    @GetMapping("all")
    public List<SprintFailureRate> getAllSprintFailureRate(){
        return sprintFailureRateServices.getAllSprintFailureRate();
    }
    @GetMapping("{period}")
    Optional<SprintFailureRate> getSprintFailureRateByPeriod(@PathVariable String period){
        return sprintFailureRateServices.getSprintFailureRateByPeriod(period);
    }

    @PutMapping("/update")
    public void calculateFailureRate(){
        sprintFailureRateServices.calculateSprintFailureRate();
    }

}

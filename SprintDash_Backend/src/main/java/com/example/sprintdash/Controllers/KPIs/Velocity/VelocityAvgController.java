package com.example.sprintdash.Controllers.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.VelocityAvg;
import com.example.sprintdash.Services.KPIs.Velocity.VelocityAvgServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/velocity/avg")
public class VelocityAvgController {
    @Autowired
    VelocityAvgServices velocityAvgServices;

    @GetMapping("/all")
    public List<VelocityAvg> getAllVelocitiesAvg(){
        return velocityAvgServices.getAllVelocitiesAvg();
    }

    @GetMapping("/{period}")
    public Optional<VelocityAvg> getVelocityAvgByPeriod(@PathVariable String period){
        return velocityAvgServices.getVelocityAvgByPeriod(period);
    }
    @PutMapping("/update")
    public void calculateVelocityAvg(){
        velocityAvgServices.calculateVelocityAvg();
    }
}

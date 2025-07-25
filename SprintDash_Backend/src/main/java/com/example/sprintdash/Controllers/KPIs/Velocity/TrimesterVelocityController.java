package com.example.sprintdash.Controllers.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.TrimesterVelocity;
import com.example.sprintdash.Services.KPIs.Velocity.TrimesterVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("velocity/trimester")
public class TrimesterVelocityController {
    @Autowired
    TrimesterVelocityServices trimesterVelocityServices;

    @GetMapping("/all")
    public List<TrimesterVelocity> getAllTrimesterVelocities(){
        return trimesterVelocityServices.getAllTrimesterVelocities();
    }

    @GetMapping("/{trimester}")
    public Optional<TrimesterVelocity> getTrimesterVelocityByTrimester(@PathVariable String trimester){
        return trimesterVelocityServices.getTrimesterVelocityByTrimester(trimester);
    }
    @PutMapping("/update")
    public void calculateTrimesterVelocity(){
        trimesterVelocityServices.calculateTrimesterVelocity();
    }

}

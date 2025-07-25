package com.example.sprintdash.Controllers.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.YearVelocity;
import com.example.sprintdash.Services.KPIs.Velocity.YearVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("velocity/year")
public class YearVelocityController {
    @Autowired
    YearVelocityServices yearVelocityServices;

    @GetMapping("/all")
    public List<YearVelocity> getAllYearVelocities(){
        return yearVelocityServices.getAllYearVelocities();
    }

    @GetMapping("/{year}")
    public Optional<YearVelocity> getYearVelocityBySemester(@PathVariable String year){
        return yearVelocityServices.getYearVelocityBySemester(year);
    }
    @PutMapping("/update")
    public void calculateYearVelocity(){
        yearVelocityServices.calculateYearVelocity();
    }
}

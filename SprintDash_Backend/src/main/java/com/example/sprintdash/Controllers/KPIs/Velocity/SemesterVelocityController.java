package com.example.sprintdash.Controllers.KPIs.Velocity;

import com.example.sprintdash.Models.KPIs.Velocity.SemesterVelocity;
import com.example.sprintdash.Services.KPIs.Velocity.SemesterVelocityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/velocity/semester")
public class SemesterVelocityController {
    @Autowired
    SemesterVelocityServices semesterVelocityServices;

    @GetMapping("/all")
    public List<SemesterVelocity> getAllSemesterVelocities(){
        return semesterVelocityServices.getAllSemesterVelocities();
    }

    @GetMapping("/{semester}")
    public Optional<SemesterVelocity> getSemesterVelocityBySemester(@PathVariable String semester){
        return semesterVelocityServices.getSemesterVelocityBySemester(semester);
    }
    @PutMapping("/update")
    public void calculateSemesterVelocity(){
        semesterVelocityServices.calculateSemesterVelocity();
    }
}

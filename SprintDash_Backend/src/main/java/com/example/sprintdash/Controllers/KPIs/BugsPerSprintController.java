package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.BugsPerSprint;
import com.example.sprintdash.Services.KPIs.BugsPerSprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sprint/bugs")
@CrossOrigin(origins = "http://localhost:4200")
public class BugsPerSprintController {
    @Autowired
    BugsPerSprintServices bugsPerSprintServices;

    @GetMapping("/all")
    public List<BugsPerSprint> getBugsPerSprint() {
        return bugsPerSprintServices.getAllBugsPerSprint();
    }

    @GetMapping("{id}")
    public Optional<BugsPerSprint> getBugsPerSprintBySprintId(@PathVariable String id){
        return bugsPerSprintServices.getBugsPerSprintBySprintId(id);
    }

    @PutMapping("/update")
    public void updateBugsPerSprint(){
        bugsPerSprintServices.updateBugsPerSprint();
    }
}

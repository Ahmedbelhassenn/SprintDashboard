package com.example.sprintdash.Controllers.KPIs;

import com.example.sprintdash.Models.KPIs.TotalBugs;
import com.example.sprintdash.Services.KPIs.TotalBugsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("total/bugs")
@CrossOrigin(origins = "http://localhost:4200")
public class TotalBugsController {
    @Autowired
    TotalBugsServices totalBugsServices;

    @GetMapping("/all")
    public List<TotalBugs> getTotalBugs() {
        return totalBugsServices.getAllTotalBugs();
    }

    @GetMapping("{period}")
    public Optional<TotalBugs> getTotalBugsBySprintId(@PathVariable String period){
        return totalBugsServices.getTotalBugs(period);
    }

    @PutMapping("/update")
    public void updateTotalBugs(){
        totalBugsServices.updateTotalBugs();
    }
}

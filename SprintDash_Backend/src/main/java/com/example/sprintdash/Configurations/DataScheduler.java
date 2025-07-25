package com.example.sprintdash.Configurations;

import com.example.sprintdash.Services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataScheduler {
    @Autowired
    UpdateService updateService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void dataScheduler() {
        updateService.updateJira();
        updateService.updateKpis();
    }
}

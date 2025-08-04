package com.example.sprintdash.Configurations;

import com.example.sprintdash.Services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "scheduling.enabled", havingValue = "true", matchIfMissing = true)
public class DataScheduler {
    @Autowired
    UpdateService updateService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void dataScheduler() {
        updateService.updateJira();
        updateService.updateKpis();
    }
}

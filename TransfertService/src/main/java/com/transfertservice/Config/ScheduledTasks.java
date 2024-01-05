package com.transfertservice.Config;

import com.transfertservice.Services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Configuration
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private TransfertService transfertService;

    @Scheduled(cron = "0 0 0 * * ?") // Exécute tous les jours à minuit
    public void planifierMiseAJourEtatTransferts() {
        transfertService.BatchmettreAJourEtatTransferts();
    }
}

package org.reactive.programming.produmer.producer.controller;

import org.reactive.programming.produmer.producer.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@EnableScheduling
public class ProducerController {

    @Autowired
    PublisherService publisherService;

    @Scheduled(cron = "0/30 * * * * *")
    public void performTaskUsingCron() {
        System.out.println("Callin producers");
        publisherService.uploadProductData();
        publisherService.uploadCustomerData();
    }
}

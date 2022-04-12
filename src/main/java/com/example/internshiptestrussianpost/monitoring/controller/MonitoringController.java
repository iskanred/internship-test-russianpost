package com.example.internshiptestrussianpost.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private final Logger logger = LoggerFactory.getLogger(MonitoringController.class);

    @GetMapping("/ping")
    public void ping() {
        logger.info("GET monitoring/ping request has been received");
    }
}

package smilyk.atsarat.sceduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smilyk.atsarat.sceduler.services.scheduler.Scheduler;

/**
 * this controller need only for checkin if scheduler work
 */
@RestController
@RequestMapping("/sc/v1")
public class ShedulerController {

    @Autowired
    Scheduler scheduler;

    @GetMapping()
    public String checkRecords() {
        scheduler.schedulerCheckRecords();
        return "scheduler is work";
    }

    }

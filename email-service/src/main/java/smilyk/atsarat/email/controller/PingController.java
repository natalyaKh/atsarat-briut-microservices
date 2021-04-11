package smilyk.atsarat.email.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController

public class PingController {
    private String currentDate = LocalDateTime.now().toLocalDate().toString();


    @GetMapping()
    public String ping() {
        return " Email-Service working " + currentDate;
    }
}

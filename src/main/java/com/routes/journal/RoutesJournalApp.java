package com.routes.journal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class RoutesJournalApp {

    public static void main(String[] args) {
        run(RoutesJournalApp.class, args);
    }
}

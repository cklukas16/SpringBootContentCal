package com.seis739.contentcalendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // String message is seen in console output AFTER everything else has started.
        System.out.println("Hola!!");
    }

    
    
}

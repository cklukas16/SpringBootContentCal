package com.seis739.contentcalendar;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// Requried for main class
@SpringBootApplication
public class ContentCalendarApplication {

	public static void main(String[] args) {
		// The magic that actually runs your application.
		SpringApplication.run(ContentCalendarApplication.class, args);

		
		// // Tutorial Notes: 
		// ConfigurableApplicationContext context = SpringApplication.run(ContentCalendarApplication.class, args);
		// context.getBeanDefinitionNames(); //this will output a String[] (see below)
		// // prints out all of the bean names that are working in the background of the application
		// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}

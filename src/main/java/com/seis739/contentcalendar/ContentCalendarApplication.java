package com.seis739.contentcalendar;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.seis739.contentcalendar.Repository.ContentRepository;
import com.seis739.contentcalendar.model.Content;
import com.seis739.contentcalendar.model.Status;
import com.seis739.contentcalendar.model.Type;

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

	// ****************************************
	// *****Data Loading***********************
	// @Bean
	// CommandLineRunner commandLineRunner(ContentRepository repository){
	// 	//return args -> System.out.println("The commandLineRunner is executed!");
		
	// 	return args -> {
	// 		// insert some data into the database

	// 		Content content = new Content(null, 
    //     	  	"CommandLineRunner", 
    //     	    "Inserting data using CommandLineRunner.", 
    //         	Status.IDEA,
    //         	Type.VIDEO, 
    //         	LocalDateTime.now(), 
    //     	    null, 
    //     	    "");

	// 	repository.save(content);
	// 	};
	// }
}

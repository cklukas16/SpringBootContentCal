package com.seis739.contentcalendar.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seis739.contentcalendar.Repository.ContentRepository;
import com.seis739.contentcalendar.model.Content;

@Component   // comment out @Component if you don't want this to run.
public class DataLoader implements CommandLineRunner {

    // to get and send things to database
    private final ContentRepository repository;
    // deserialize json into objects
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper){
        this.repository = repository;
        this.objectMapper = objectMapper;
    }


    @Override
    public void run(String... args) throws Exception {
        // String message is seen in console output AFTER everything else has started.
        // System.out.println("Hola!!");

        //*******Production */
        // Read from content.json
        // getResourceAsStream finds a resouce with a given name.
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
            // objectMapper reads json fron inputStream and creates a type from each item
            // TypeReference is an abstract class that gives a reference of the data type you want after
            // json parsing is complete. 
            repository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
        }
    }

    
    
}

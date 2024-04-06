package com.seis739.contentcalendar.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Configuration is required for defining a bean within this class. 
@Configuration
public class MyWebConfig {

    // a client for making public or other API calls
    // returns a new instance of the template
    // Can't use @Component because that's class level
    // Name of the bean is the method name.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
    
}

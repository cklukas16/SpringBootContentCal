package com.seis739.contentcalendar.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotBlank;

// Records are different from classes. Instead of properties, they have components defined in the ()
public record Content(
    @Id
    Integer id, 
    @NotBlank // Can't be null AND must have 1 non-whitespace character. @NotEmpty is another option
    String title, 
    @Column(value = "description") // avoids using POSTGRES keyword 'desc'
    String desc,
    Status status, // ENUM created in Status.java
    Type contentType, // ENUM created in Type.java
    LocalDateTime dateCreated, 
    LocalDateTime dateUpdated, 
    String url
) {
    
}
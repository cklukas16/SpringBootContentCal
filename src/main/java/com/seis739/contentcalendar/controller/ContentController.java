package com.seis739.contentcalendar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;  // replaces all above
import com.seis739.contentcalendar.Repository.ContentCollectionRepository;
import com.seis739.contentcalendar.model.Content;

import jakarta.validation.Valid;

// Create an instance of this controller and put it in the ApplicationContext
@RestController
// Allows back and to talk to front end and avoid CORS errors. 
@CrossOrigin
@RequestMapping(path = "/api/content")
public class ContentController {

    // Final because we will only mess with it once?
    private final ContentCollectionRepository repository;

    // Dependency injection via param because @Repository
    public ContentController(ContentCollectionRepository repository){
        this.repository = repository;
    }

    // Make a request and find all the pieces of content in the system
    @GetMapping(path = "") // handles get requests. Path is '/api/content' because of above @RequestMapping
    public List<Content> findAll(){
        return repository.findAll();
    }

    // Get a piece of content by the ID
    // {dynamic search} plus @PathVariable assigns whatever is in the path to the specified argument name
    @GetMapping(path = "/{id}")
    public Content findByID(@PathVariable Integer id){
        Optional<Content> c = repository.findById(id);
        return c.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found."));
    }

    // Need to make post call. Need a way to tell Spring that Content param is part of request body.
    // @RequestBody Indicates that a method parameter should be bound to the body of the web request.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){
        // make sure id exists
        if(!repository.existsById(id)){
            // In reality would create a custom exception for this. 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
        }
        else{
            repository.save(content);
        }    
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        repository.delete(id);
    }
}

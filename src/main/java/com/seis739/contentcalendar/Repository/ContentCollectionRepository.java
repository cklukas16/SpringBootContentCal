package com.seis739.contentcalendar.Repository;
import com.seis739.contentcalendar.model.Content;
import com.seis739.contentcalendar.model.Status;
import com.seis739.contentcalendar.model.Type;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }
    
    // Optional = A container object which may or may not contain a non-null value. 
    //            isPresent() returns true if a value is present and false if the object is "empty".
    public Optional<Content> findById(Integer id){
        // convert to a stream and filter ***
        // give me the id of the content and see if it equals the id passed as a param
        // if they are equal, find the first one. That Optional gets returned. 
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    // *** Should really be broken out into 2 separate methods!!
    public void save(Content content) {
        // Remove any existing content with the matching id first. 
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

    // Creating some dummy data and adding to the content list for API testing. 
    @PostConstruct
    private void init(){
        Content content = new Content(1, 
            "My First Blog Post", 
            "My first blog post.", 
            Status.IDEA,
            Type.ARTICLE, 
            LocalDateTime.now(), 
            null, 
            "");
    
        contentList.add(content);
    }


}

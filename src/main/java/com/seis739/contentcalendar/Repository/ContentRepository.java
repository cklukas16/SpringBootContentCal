package com.seis739.contentcalendar.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.seis739.contentcalendar.model.Content;
import com.seis739.contentcalendar.model.Status;

// ListCrudRepository is built in to Spring Data
// Give it the type of object it is managing and the object's @ID. 
public interface ContentRepository extends ListCrudRepository<Content, Integer>{
    
    // Query Derivation
    // Abstract method with definition of bits that need to work at runtime. 
    List<Content> findAllByTitleContains(String keyword);

    // Alternative Option:
    // Gives more control over the database
    @Query("""
            SELECT * FROM Content
            where status = :status
            """)
    // @Param binds to the :status in the query
    List<Content> listByStatus(@Param("status") Status status);
}

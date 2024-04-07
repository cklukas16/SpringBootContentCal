package com.seis739.contentcalendar.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.seis739.contentcalendar.model.Content;
import com.seis739.contentcalendar.model.Status;
import com.seis739.contentcalendar.model.Type;


// Example class to demonstrate different levels of abstraction when 
// working with the JDBC Api (notes p.15-16) Option 2A

@Repository
public class ContentJdbcTemplateRepository {

    // Note: We did not make a class for this. With Spring dependency injection
    // we automatically have use of JdbcTemplate class.
    // Can use this to talk to the database. 
    private final JdbcTemplate jdbcTemplate;
    
    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // RowMapper:  Takes a row from the result set and extracts data into a new Content object.
    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"), 
        rs.getString("title"), 
        rs.getString("desc"),
        Status.valueOf(rs.getString("status")),
        Type.valueOf(rs.getString("content_type")), 
        rs.getObject("date_created", LocalDateTime.class), 
        rs.getObject("date_updated", LocalDateTime.class),
        //rs.getTimestamp("date_created"), 
        //rs.getTimestamp("date_updated"),
        rs.getString("url"));
    }

    // Retrieves all data from Content table using the sql statement and RowMapper. 
    // RowMapper = how to translate the table row into an object that the program can work with.
    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        List<Content> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
        return contents;
    }

    public void createContent(String title, String desc, Status status, Type contentType, String URL){
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL);
    }

    public void updateContent(int id, String title, String desc, Status status, Type contentType, String URL){
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    public void deleteContent(int id){
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Content getContent(int id){
        String sql = "SELECT * FROM Content WHERE id=?";
        //Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        Content content = jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow);
        return content;
    }
}

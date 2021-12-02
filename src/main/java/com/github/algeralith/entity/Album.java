package com.github.algeralith.entity;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany // An Album contains many images. Thus, one-to-many relationship.
    private List<Image> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString()
    {
        String value = "";
        ObjectMapper objectMapper = new ObjectMapper();

        try  {
            value = objectMapper.writeValueAsString(this);
        } catch (Exception e) {
        }

        return value;
    }

}
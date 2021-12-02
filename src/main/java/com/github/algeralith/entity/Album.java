package com.github.algeralith.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="Albums")
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    public Album() {
    }
    
    public Album(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
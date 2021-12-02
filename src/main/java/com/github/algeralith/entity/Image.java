package com.github.algeralith.entity;

import java.security.spec.ECFieldF2m;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.impl.JsonUtil;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToMany // Multiple albums can reference the same image. Therefore, this should be a many-to-many relationship.
    public List<Album> albums; // TODO :: Let this use the same FK as the OneToOne for Product to album.

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

    public List<Album> getAlbums() {
        return albums;
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
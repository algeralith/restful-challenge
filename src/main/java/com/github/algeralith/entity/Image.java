package com.github.algeralith.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "images")
    public List<Album> albums = new ArrayList<>();

    public Image() {
    }

    public Image(long id) {
        this.id = id;
    }

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

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
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
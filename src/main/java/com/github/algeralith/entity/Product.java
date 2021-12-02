package com.github.algeralith.entity;

import javax.persistence.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne // A product may only belong to one Album, and one album belongs to only one product. One-to-one relationship.
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlbum(Album album)  {
        this.album = album;
    }

    public Album getAlbum()  {
        return album;
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
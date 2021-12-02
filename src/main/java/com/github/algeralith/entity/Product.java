package com.github.algeralith.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;

@Entity
@Table(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToOne()
    private Album album;

    public Product() {

    }

    public Product(long id) {
        this.id = id;
    }

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
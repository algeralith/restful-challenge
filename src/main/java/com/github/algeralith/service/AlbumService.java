package com.github.algeralith.service;

import com.github.algeralith.entity.Album;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlbumService extends EntityCrudService<Album> {

    public AlbumService() {
        super(Album.class);
    }
}

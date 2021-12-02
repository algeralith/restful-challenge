package com.github.algeralith.service;

import com.github.algeralith.entity.Album;

public class AlbumService extends EntityCrudService<Album> {

    public AlbumService() {
        super(Album.class);
    }
}

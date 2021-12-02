package com.github.algeralith.service;

import com.github.algeralith.entity.Image;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImageService extends EntityCrudService<Image> {

    public ImageService() {
        super(Image.class);
    }
}

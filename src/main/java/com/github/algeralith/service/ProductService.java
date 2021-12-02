package com.github.algeralith.service;

import com.github.algeralith.entity.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService extends EntityCrudService<Product> {

    public ProductService() {
        super(Product.class);
    }
}

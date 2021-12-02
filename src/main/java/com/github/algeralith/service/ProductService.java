package com.github.algeralith.service;
import com.github.algeralith.entity.Product;

public class ProductService extends EntityCrudService<Product> {

    public ProductService() {
        super(Product.class);
    }
}

package com.main.service;

import com.main.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> findAll();

    List<Products> findByProductTypeId(long productTypeId);

    List<Products> findByCategoryId(Long categoryId);

    Products findByProductId(String productId);

    void save(Products products);
}

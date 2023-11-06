package com.main.service;

import com.main.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Products> findAll();

    List<Products> findByProductTypeId(int productTypeId);

    List<Products> findTopProductByCategoryId(int categoryId);

    List<Products> findByProductTypeId(long productTypeId);

    List<Products> findByCategoryId(Long categoryId);

    Products findProductByProductId(String productId);

    Optional<Products> findByProductId(String productId);

    void save(Products products);

    boolean doesProductExist(String productId);
}

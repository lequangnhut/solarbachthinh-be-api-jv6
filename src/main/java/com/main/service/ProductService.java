package com.main.service;

import com.main.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> findAll();

    List<Products> findByProductTypeId(int productTypeId);

    List<Products> findTopProductByCategoryId(int categoryId);

    Products findByProductId(String productId);
}

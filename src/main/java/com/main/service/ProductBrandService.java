package com.main.service;

import com.main.entity.ProductBrands;

public interface ProductBrandService {

    List<ProductBrands> findAll();

    ProductBrands findByProductBrandId(String productBrandId);
}
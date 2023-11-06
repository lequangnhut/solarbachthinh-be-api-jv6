package com.main.service;

import com.main.entity.ProductBrands;

import java.util.List;

public interface ProductBrandService {

    List<ProductBrands> findAll();

    ProductBrands findByProductBrandId(String productBrandId);
}
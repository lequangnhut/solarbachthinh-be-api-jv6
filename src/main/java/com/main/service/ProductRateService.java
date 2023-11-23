package com.main.service;

import com.main.entity.ProductRate;

import java.util.List;

public interface ProductRateService {
    List<ProductRate> findAll();
    boolean existsById(String id);
    List<ProductRate> findAllRateByProductId(String productId);
    void saveProductRate(ProductRate productRate);
}

package com.main.service.impl;

import com.main.entity.Products;
import com.main.repository.ProductRepository;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;

    @Override
    public List<Products> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Products> findByProductTypeId(long productTypeId) {
        return null;
    }

    @Override
    public List<Products> findByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public Products findByProductId(String productId) {
        return repo.getReferenceById(productId);
    }

    @Override
    public void save(Products products) {

    }
}

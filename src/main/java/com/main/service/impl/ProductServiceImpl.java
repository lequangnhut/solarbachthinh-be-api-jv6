package com.main.service.impl;

import com.main.entity.Products;
import com.main.repository.ProductsRepository;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;


    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public List<Products> findByProductTypeId(int productTypeId) {
        return productsRepository.findByProductTypeId(productTypeId);
    }

    @Override
    public List<Products> findTopProductByCategoryId(int categoryId) {
        return productsRepository.findByCategoryId(categoryId);
    }

    @Override
    public Products findByProductId(String productId) {
        return productsRepository.getReferenceById(productId);
    }
}

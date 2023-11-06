package com.main.service.impl;

import com.main.entity.ProductBrands;
import com.main.repository.ProductBrandRepository;
import com.main.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    ProductBrandRepository productBrandRepository;
    @Override
    public List<ProductBrands> findAll() {
        return productBrandRepository.findAll();
    }
}

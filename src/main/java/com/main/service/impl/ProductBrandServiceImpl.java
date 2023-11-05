package com.main.service.impl;

import com.main.entity.ProductBrands;
import com.main.repository.ProductBrandRepository;
import com.main.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    ProductBrandRepository productBrandRepository;

    @Override
    public ProductBrands findByProductBrandId(String productBrandId) {
        return productBrandRepository.getReferenceById(productBrandId);
    }
}

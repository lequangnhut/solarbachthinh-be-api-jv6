package com.main.service;

import com.main.entity.ProductCategories;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryService {

    List<ProductCategories> findAll();

    List<ProductCategories> findAllTop4(Pageable pageable);

    ProductCategories findById(int id);

    ProductCategories save(ProductCategories productCategories);

    void delete(int id);
}

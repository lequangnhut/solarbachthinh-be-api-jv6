package com.main.repository;

import com.main.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, String> {

    List<Products> findAllByOrderByDateCreatedDesc();

    List<Products> findByProductTypeId(long productTypeId);
}
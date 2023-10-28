package com.main.repository;

import com.main.entity.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributes, Integer> {
}
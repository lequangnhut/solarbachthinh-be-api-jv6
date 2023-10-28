package com.main.repository;

import com.main.entity.ProductBrands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrands, String> {
}
package com.main.repository;

import com.main.entity.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRateRepository extends JpaRepository<ProductRate, String> {
    List<ProductRate> findByProductId(String productId);
    boolean existsById(String productId);
}

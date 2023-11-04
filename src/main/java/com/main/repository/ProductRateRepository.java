package com.main.repository;

import com.main.entity.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRateRepository extends JpaRepository<ProductRate, Integer> {

    List<ProductRate>findAllById(int id);
}

package com.main.repository;

import com.main.entity.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypesRepository extends JpaRepository<ProductTypes, Integer> {
}
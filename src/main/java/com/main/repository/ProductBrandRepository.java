package com.main.repository;

import com.main.entity.ProductBrands;
import com.main.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface ProductBrandRepository extends JpaRepository<ProductBrands, String> {

    @Query("SELECT pb FROM ProductBrands pb " +
            "JOIN pb.productsById pt " +
            "WHERE pt.productBrandId = :productBrandId")
    ProductBrands findBandByProductId(String productBrandId);
}
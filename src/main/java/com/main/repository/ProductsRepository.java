package com.main.repository;

import com.main.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, String> {

    List<Products> findByProductTypeId(int productTypeId);

    List<Products> findByProductNameContaining(String search);

    @Query("SELECT p, br FROM Products p " +
            "JOIN p.productBrandsByProductBrandId br " +
            "JOIN p.productTypesByProductTypeId pt " +
            "JOIN pt.productCategoriesByCategoryId c  " +
            "WHERE c.id = :categoryId " +
            "ORDER BY p.dateCreated DESC LIMIT 10"
    )
    List<Object[]> findByCategoryId(@Param("categoryId") int categoryId);

    @Query("SELECT p FROM Products p " +
            "JOIN p.productTypesByProductTypeId pt " +
            "JOIN pt.productCategoriesByCategoryId pc " +
            "WHERE pc.id = :categoryId")
    List<Products> findByCategoryBProductTypeByProducts(@Param("categoryId") int categoryId);



}
package com.main.repository;

import com.main.entity.ProductComment;
import com.main.entity.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Integer> {

    List<ProductComment> findAllById(int id);

}

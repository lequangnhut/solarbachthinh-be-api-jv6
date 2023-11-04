package com.main.repository;

import com.main.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Integer> {


}

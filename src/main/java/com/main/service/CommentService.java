package com.main.service;

import com.main.entity.ProductComment;

import java.util.List;

public interface CommentService {
    List<ProductComment> findAll();
}

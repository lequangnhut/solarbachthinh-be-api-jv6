package com.main.service.impl;

import com.main.entity.ProductComment;
import com.main.repository.ProductCommentRepository;
import com.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ProductCommentRepository repo;

    @Override
    public List<ProductComment> findAll() {
        return null;
    }
}

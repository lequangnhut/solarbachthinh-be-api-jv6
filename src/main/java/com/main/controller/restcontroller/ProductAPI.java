package com.main.controller.restcontroller;

import com.main.entity.ProductCategories;
import com.main.entity.Products;
import com.main.service.CategoryService;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api")
public class ProductAPI {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("product")
    public List<Products> productList() {
        return productService.findAll();
    }

    @GetMapping("product/get-top4-category")
    public List<ProductCategories> showCategory() {
        Pageable pageable = PageRequest.of(0, 4);
        return categoryService.findAllTop4(pageable);
    }

    @GetMapping("product/{categoryId}")
    public List<Products> productGetById(@PathVariable int categoryId) {
        return productService.findTopProductByCategoryId(categoryId);
    }
}

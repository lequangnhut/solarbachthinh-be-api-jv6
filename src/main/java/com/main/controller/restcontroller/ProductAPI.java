package com.main.controller.restcontroller;

import com.main.entity.ProductCategories;
import com.main.entity.Products;
import com.main.service.ProductCategoryService;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api")
public class ProductAPI {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;

    @GetMapping("product")
    public List<Products> productList() {
        return productService.findAll();
    }

    // tìm ra product bằng mã
    @GetMapping("product/find-by-id/{productId}")
    public ResponseEntity<Products> getAccountById(@PathVariable String productId) {
        Products product = productService.findProductByProductId(productId);
        return ResponseEntity.ok().body(product);
    }

    // tìm ra 4 danh mục đầu tiên
    @GetMapping("product/get-top4-category")
    public List<ProductCategories> showCategory() {
        Pageable pageable = PageRequest.of(0, 4);
        return productCategoryService.findAllTop4(pageable);
    }

    // tìm ra sản phẩm bằng mã danh mục
    @GetMapping("product/{categoryId}")
    public List<Object[]> productGetById(@PathVariable int categoryId) {
        return productService.findTopProductByCategoryId(categoryId);
    }
}

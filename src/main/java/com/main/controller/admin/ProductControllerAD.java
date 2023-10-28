package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/san-pham")
public class ProductControllerAD {

    @GetMapping
    public String product() {
        return "views/admin/page/views/products-list";
    }

    @GetMapping("them-san-pham")
    public String showAddProduct() {
        return "views/admin/page/crud/product/products-add";
    }

    @GetMapping("sua-san-pham")
    public String showEditProduct() {
        return "views/admin/page/crud/product/products-edit";
    }
}

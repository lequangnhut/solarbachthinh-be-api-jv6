package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/the-loai")
public class ProductTypeControllerAD {

    @GetMapping
    public String product_types() {
        return "views/admin/page/views/product-type-list";
    }

    @GetMapping("them-the-loai")
    public String show_product_types_add() {
        return "views/admin/page/crud/product_type/product-type-add";
    }

    @GetMapping("sua-the-loai")
    public String show_product_types_edit() {
        return "views/admin/page/crud/product_type/product-type-edit";
    }
}

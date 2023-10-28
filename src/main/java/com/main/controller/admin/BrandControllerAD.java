package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/danh-sach-thuong-hieu")
public class BrandControllerAD {

    @GetMapping
    public String showBrands() {
        return "views/admin/page/views/brands";
    }

    @GetMapping("them-thuong-hieu")
    public String showAddBrands() {
        return "views/admin/page/crud/brand/brand-add";
    }

    @GetMapping("chinh-sua-thuong-hieu")
    public String showEditBrands() {
        return "views/admin/page/crud/brand/brand-edit";
    }
}

package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/danh-muc")
public class CategoryControllerAD {

    @GetMapping
    public String categories() {
        return "views/admin/page/views/categorys-list";
    }

    @GetMapping("them-danh-muc")
    public String categorys_add() {
        return "views/admin/page/crud/category/categorys-add";
    }

    @GetMapping("sua-danh-muc")
    public String categorys_edit() {
        return "views/admin/page/crud/category/categorys-edit";
    }
}

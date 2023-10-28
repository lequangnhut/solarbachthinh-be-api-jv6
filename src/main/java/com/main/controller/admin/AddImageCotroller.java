package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/sua-hinh-anh")
public class AddImageCotroller {

    @GetMapping
    public String image_add() {
        return "views/admin/page/crud/image/image-edit";
    }
}

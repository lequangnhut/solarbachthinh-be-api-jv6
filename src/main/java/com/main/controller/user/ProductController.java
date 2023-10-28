package com.main.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("san-pham")
public class ProductController {

    @GetMapping
    public String showProducts() {
        return "views/user/page/product";
    }
}

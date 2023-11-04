package com.main.controller.user;

import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"trang-chu", "/", ""})
public class IndexController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.findTopProductByCategoryId(1));
        return "views/user/index";
    }
}

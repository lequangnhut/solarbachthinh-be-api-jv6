package com.main.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"trang-chu", "/", ""})
public class IndexController {

    @GetMapping
    public String index() {
        return "views/user/index";
    }
}

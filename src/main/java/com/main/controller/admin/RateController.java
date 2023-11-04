package com.main.controller.admin;

import com.main.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/danh-gia")
public class RateController {

    @Autowired
    RateService rateService;

    @GetMapping
    public String product_rate(Model model) {
        model.addAttribute("Rates", rateService.findAll());
        return "views/admin/page/views/rate-list";

    }
}

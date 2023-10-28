package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/dashboard")
public class DashboardControllerAD {

    @GetMapping
    public String showDashboard() {
        return "views/admin/page/views/dashboard";
    }
}

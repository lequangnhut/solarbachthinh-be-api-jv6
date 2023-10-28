package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri")
public class ConfirmOrderController {

    @GetMapping("xac-nhan-don-hang")
    public String confirm_order() {
        return "views/admin/page/views/confirm-order-list";
    }

    @GetMapping("don-da-van-chuyen")
    public String showOrderShip() {
        return "views/admin/page/views/order-ship-list";
    }

    @GetMapping("don-da-huy")
    public String showOrderClose() {
        return "views/admin/page/views/order-close-list";
    }
}

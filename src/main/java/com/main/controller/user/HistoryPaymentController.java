package com.main.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lich-su-mua-hang")
public class HistoryPaymentController {

    @GetMapping
    public String showListPayment() {
        return "views/user/page/history-payment";
    }
}

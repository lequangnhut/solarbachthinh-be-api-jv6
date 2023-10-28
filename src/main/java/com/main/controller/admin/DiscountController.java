package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/giam-gia")
public class DiscountController {

    @GetMapping
    public String showDiscount() {
        return "views/admin/page/views/discouts-list";
    }

    @GetMapping("danh-sach-ma-giam-gia-da-sua-dung")
    public String showAccountDiscountCode() {
        return "views/admin/page/views/apply-discouts-list";
    }

    @RequestMapping("them-ma")
    public String showAddDiscount() {
        return "views/admin/page/crud/discout/discout-add";
    }

    @RequestMapping("them-ma/random-code")
    public String randomDiscountCode() {
        return "views/admin/page/crud/discout/discout-add";
    }

    @GetMapping("sua-ma")
    public String showEditDiscount() {
        return "views/admin/page/crud/discout/discout-edit";
    }
}

package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/khach-hang")
public class CustomerControllerAD {

    @GetMapping
    public String dataCustomer() {
        return "views/admin/page/views/customers-list";
    }

    @GetMapping("them-khach-hang")
    public String customer_add() {
        return "views/admin/page/crud/customer/customer-add";
    }

    @GetMapping("sua-khach-hang")
    public String getCustomer_edit() {
        return "views/admin/page/crud/customer/customer-edit";
    }
}

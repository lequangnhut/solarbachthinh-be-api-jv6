package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/tai-khoan")
public class AccountControllerAD {

    @GetMapping
    public String dataAccount() {
        return "views/admin/page/views/accounts-list";
    }

    @GetMapping("them-tai-khoan")
    public String account_add() {
        return "views/admin/page/crud/account/account-add";
    }

    @GetMapping("sua-tai-khoan")
    public String account_edit() {
        return "views/admin/page/crud/account/account-edit";
    }
}
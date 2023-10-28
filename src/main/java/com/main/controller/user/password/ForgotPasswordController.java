package com.main.controller.user.password;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quen-mat-khau")
public class ForgotPasswordController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String showForgot() {
        return "views/user/page/password/forgot-password";
    }

    @GetMapping("xac-nhan-otp")
    public String showVerify() {
        return "views/user/page/password/verify";
    }

    @GetMapping("mat-khau-moi")
    public String showNewPass() {
        return "views/user/page/password/new-password";
    }
}

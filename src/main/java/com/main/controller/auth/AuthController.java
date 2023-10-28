package com.main.controller.auth;

import com.main.dto.UsersDto;
import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.UserService;
import com.main.utils.EntityDtoUtils;
import com.main.utils.ParamService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    ParamService paramService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmailService emailService;

    @Autowired
    HttpSession session;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //    xác thực email
    @GetMapping("xac-thuc-email")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        Users users = userService.findByToken(token);

        if (users != null) {
            String email = users.getEmail();
            model.addAttribute("email", email);
            users.setAcctive(Boolean.TRUE);
            userService.update(users);
        }
        return "views/user/page/verify-success";
    }

    //    đăng ký
    @GetMapping("dang-ky")
    public String showSighupPage(Model model) {
        model.addAttribute("user", new UsersDto());
        return "views/user/page/auth/sign-up";
    }

    @PostMapping("dang-ky/submit")
    public String registerSave(@Valid @ModelAttribute("user") UsersDto usersDto, BindingResult result, Model model) {
        Users exitsUser = userService.findByEmail(usersDto.getEmail());

        if (exitsUser != null && exitsUser.getEmail() != null && !exitsUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Email đã tồn tại ở một tài khoản khác !");
        }
        if (exitsUser != null && exitsUser.getPhoneNumber() != null && !exitsUser.getPhoneNumber().isEmpty()) {
            result.rejectValue("phoneNumber", null, "Số điện thoại đã tồn tại ở một tài khoản khác !");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", usersDto);
            return "views/user/page/auth/sign-up";
        }

        Users usersMap = EntityDtoUtils.convertToEntity(usersDto, Users.class);
        userService.save(usersMap);

        session.setAttribute("centerSuccess", "Đăng ký tài khoản thành công, vui lòng xác thực email !");
        emailService.queueEmailRegister(usersDto);
        return "redirect:/dang-nhap";
    }

    //    đăng nhập user
    @GetMapping("dang-nhap")
    public String showLoginPage() {
        return "views/user/page/auth/login";
    }

//    @PostMapping("dang-nhap/submit")
//    public String submitLogin(@RequestParam("username") String email, @RequestParam("password") String password) {
//        Users users = userService.findByEmail(email);
//
//        if (email != null && password != null && users != null) {
//            String pwInDB = users.getPasswords();
//
//            if (encoder.matches(password, pwInDB)) {
//                session.setAttribute(SessionAttr.CURRENT_USER, users);
//                session.setAttribute("toastSuccess", "Đăng nhập thành công !");
//
//                return "redirect:/trang-chu";
//            }
//
//            session.setAttribute("toastFailed", "Sai thông tin đăng nhập !");
//        }
//
//        return "redirect:/dang-nhap";
//    }

    //    đănh nhập admin
    @GetMapping("admin")
    public String showFormLogin() {
        return "views/admin/login";
    }
}

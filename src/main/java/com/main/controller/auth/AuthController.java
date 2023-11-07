package com.main.controller.auth;

import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.UserService;
import com.main.utils.ParamService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    UserDetailsService userDetailsService;

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

    //xác thực email
    @GetMapping("xac-thuc-email")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        Users users = userService.findByToken(token);

        if (users != null) {
            String email = users.getEmail();
            model.addAttribute("email", email);
            users.setAcctive(Boolean.TRUE);
            userService.update(users);
        }
        return "views/user/page/password/verify-success";
    }

    // post đăng ký
    @PostMapping("dang-ky/submit")
    public String registerSave() {

//        Users usersMap = EntityDtoUtils.convertToEntity(usersDto, Users.class);
//        userService.save(usersMap);
//
//        session.setAttribute("centerSuccess", "Đăng ký tài khoản thành công, vui lòng xác thực email !");
//        emailService.queueEmailRegister(usersDto);
        return "redirect:#!/dang-nhap";
    }

    // gán giá trị cho trang tạm để lưu session
    @GetMapping("page-temp")
    public String pageTemp(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Users users = userService.findByEmail(userDetails.getUsername());

        session.setAttribute(SessionAttr.CURRENT_USER, users);
        session.setAttribute("toastSuccess", "Đăng nhập thành công !");
        return "redirect:#!/trang-chu";
    }

    // xoá session
    @GetMapping("logout-temp")
    public String logoutTemp() {
        session.removeAttribute(SessionAttr.CURRENT_USER);
        return "redirect:#!/trang-chu";
    }
}

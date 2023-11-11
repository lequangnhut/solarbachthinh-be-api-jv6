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
import org.springframework.web.bind.annotation.GetMapping;
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
    public String verifyEmail(@RequestParam("token") String token) {
        Users users = userService.findByToken(token);

        if (users != null) {
            users.setAcctive(Boolean.TRUE);
            userService.update(users);
        }
        return "redirect:/#!xac-thuc-tai-khoan";
    }

    // gán giá trị cho trang tạm để lưu session
    @GetMapping("redirect")
    public String pageRedirect(Principal principal) {
        if (principal != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
            Users users = userService.findByEmail(userDetails.getUsername());

            if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
                if (users != null) {
                    session.setAttribute(SessionAttr.CURRENT_USER, users);
                    session.setAttribute("toastSuccess", "Đăng nhập thành công !");
                    return "redirect:/quan-tri/san-pham";
                }

            } else if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("USER"))) {
                if (users != null) {
                    session.setAttribute(SessionAttr.CURRENT_USER, users);
                    session.setAttribute("toastSuccess", "Đăng nhập thành công !");
                    return "redirect:#!/trang-chu";
                }
            } else {
                return "redirect:/error";
            }
        }
        session.setAttribute("toastFailed", "Sai thông tin đăng nhập !");
        return "redirect:#!/dang-nhap";
    }

    // xoá session
    @GetMapping("redirect-logout")
    public String logoutTemp() {
        session.removeAttribute(SessionAttr.CURRENT_USER);
        return "redirect:#!/trang-chu";
    }
}

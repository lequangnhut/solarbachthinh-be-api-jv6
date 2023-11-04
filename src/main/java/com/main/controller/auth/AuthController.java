package com.main.controller.auth;

import com.main.dto.UsersDto;
import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.UserService;
import com.main.utils.EntityDtoUtils;
import com.main.utils.ParamService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

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
        return "views/user/page/verify-success";
    }

    //đăng ký
    @GetMapping("dang-ky")
    public String showSighupPage(Model model) {
        model.addAttribute("user", new UsersDto());
        return "views/user/page/auth/sign-up";
    }

    @PostMapping("dang-ky/submit")
    public String registerSave(@Valid @ModelAttribute("user") UsersDto usersDto, BindingResult result, Model model) {
        Users exitsEmail = userService.findByEmail(usersDto.getEmail());
        Users exitsPhone = userService.findByPhone(usersDto.getPhoneNumber());

        if (exitsEmail != null && exitsEmail.getEmail() != null && !exitsEmail.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Email đã tồn tại ở một tài khoản khác !");
        }
        if (exitsPhone != null && exitsPhone.getPhoneNumber() != null && !exitsPhone.getPhoneNumber().isEmpty()) {
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

    //đăng nhập user
    @GetMapping("dang-nhap")
    public String showLoginPage() {
        return "views/user/page/auth/login";
    }

    //gán giá trị cho trang tạm để lưu session
    @GetMapping("page-temp")
    public String pageTemp(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Users users = userService.findByEmail(userDetails.getUsername());

        session.setAttribute(SessionAttr.CURRENT_USER, users);
        session.setAttribute("toastSuccess", "Đăng nhập thành công !");
        return "redirect:/trang-chu";
    }

    //kiểm tra email và phone ra ngoài view
    @PostMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        Users exitsEmail = userService.findByEmail(email);
        response.put("email", exitsEmail != null && exitsEmail.getEmail() != null && !exitsEmail.getEmail().isEmpty());
        return response;
    }

    @PostMapping("/check-phone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestParam String phone) {
        Map<String, Boolean> response = new HashMap<>();
        Users exitsPhone = userService.findByPhone(phone);
        response.put("phone", exitsPhone != null && exitsPhone.getPhoneNumber() != null && !exitsPhone.getPhoneNumber().isEmpty());
        return response;
    }
}

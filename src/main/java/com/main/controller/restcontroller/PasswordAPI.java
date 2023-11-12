package com.main.controller.restcontroller;

import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.UserService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8080")
public class PasswordAPI {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    HttpSession session;

    @Autowired
    PasswordEncoder encoder;

    @PutMapping("/change-password/{userId}")
    public ResponseEntity<Users> changePassword(@PathVariable int userId, @RequestParam("currentPass") String currentPass, @RequestParam("newPass") String newPass) {
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);

        if (users == null) {
            session.setAttribute("toastFailed", "Đổi mật khẩu thất bại!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }  else if (currentPass.equals(newPass)) {
            session.setAttribute("centerFailed", "Mật khẩu mới không được trùng với mật khẩu cũ !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } else {
            String passwordEncore = encoder.encode(newPass);
            userService.updatePass(userId, passwordEncore);

            session.setAttribute("centerSuccess", "Đổi mật khẩu thành công, bạn vui lòng đăng nhập lại !");
            session.removeAttribute(SessionAttr.CURRENT_USER);
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("check-current-password")
    public Map<String, Boolean> checkCurrentPassAPI(@RequestParam("currentPass") String currentPass) {
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
        Map<String, Boolean> response = new HashMap<>();

        if (users != null) {
            boolean isCurrentPassCorrect = encoder.matches(currentPass, users.getPasswords());
            if (isCurrentPassCorrect) {
                System.out.println("Mật khẩu hiện tại đúng");
                response.put("exists", true);
            } else {
                System.out.println("Mật khẩu hiện tại sai");
                response.put("exists", false);
            }
        }
        return response;
    }
}

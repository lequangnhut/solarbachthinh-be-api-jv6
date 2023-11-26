package com.main.controller.admin;

import com.main.dto.UsersDto;
import com.main.entity.Roles;
import com.main.entity.Users;
import com.main.service.RoleService;
import com.main.service.UserService;
import com.main.utils.EncodeUtils;
import com.main.utils.EntityDtoUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("quan-tri/tai-khoan")
public class UserControllerAD {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    HttpSession session;

    @Autowired
    PasswordEncoder encoder;

    public static boolean successMessage = false;

    @GetMapping
    public String dataAccount(Model model) {
        List<Users> users = userService.findByActiveIsTrue();

        for (Users user : users) {
            String encodedPhone = EncodeUtils.encodePhoneNumber(user.getPhoneNumber());
            user.setPhoneNumber(encodedPhone);
        }
        model.addAttribute("dataAccount", users);
        return "views/admin/page/views/accounts-list";
    }

    @GetMapping("them-tai-khoan")
    public String getAccount_add(Model model) {
        model.addAttribute("usersDto", new UsersDto());
        return "views/admin/page/crud/account/account-add";
    }

    @PostMapping("them-tai-khoan")
    public String postAccount_add(@Valid UsersDto usersDto) {
        usersDto.setAcctive(true);
        Users users = EntityDtoUtils.convertToEntity(usersDto, Users.class);
        userService.save(users);
        session.setAttribute("toastSuccess", "Thêm thành công!");
        successMessage = true;
        return "views/admin/page/crud/account/account-add";
    }

    @GetMapping("sua-tai-khoan/{userId}")
    public String account_edit(@PathVariable int userId, Model model) {
        Users users = userService.findById(userId);

        model.addAttribute("password", users.getPassword());
        model.addAttribute("usersDto", EntityDtoUtils.convertToDto(users, Users.class));
        model.addAttribute("users", users);
        return "views/admin/page/crud/account/account-edit";
    }

    @PostMapping("sua-tai-khoan")
    public String account_edit(@ModelAttribute("usersDto") UsersDto usersDto) {
        userService.update(EntityDtoUtils.convertToEntity(usersDto, Users.class));

        session.setAttribute("toastSuccess", "Cập nhật thành công !");
        return "redirect:/quan-tri/tai-khoan";
    }

    @GetMapping("xoa-tai-khoan/{id}")
    public String account_delete(@PathVariable("id") int id) {
        Users users = userService.findById(id);
        users.setAcctive(Boolean.FALSE);
        userService.update(users);
        session.setAttribute("toastSuccess", "Xoá thành công !");
        return "redirect:/quan-tri/tai-khoan";
    }

    @ModelAttribute("rolesDto")
    public List<Roles> roles() {
        return roleService.findAllRoles();
    }
}
package com.main.controller.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.dto.RegisterDto;
import com.main.dto.UsersDto;
import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.UserService;
import com.main.utils.EntityDtoUtils;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8080")
public class UserAPI {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    HttpSession session;

    @GetMapping("user")
    public List<Users> displayMessage() {
        return userService.findAllUser();
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Users> getAccountById(@PathVariable int userId) {
        Users users = userService.findById(userId);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("user/session-user")
    public ResponseEntity<String> sessionUser() {
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);

        if (users != null) {
            UsersDto usersDto = new UsersDto();
            usersDto.setId(users.getId());
            usersDto.setEmail(users.getEmail());
            usersDto.setFullname(users.getFullname());
            usersDto.setPhoneNumber(users.getPhoneNumber());
            usersDto.setDistrictName(users.getDistrictName());
            usersDto.setProvinceName(users.getProvinceName());
            usersDto.setWardName(users.getWardName());
            usersDto.setAddress(users.getAddress());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(usersDto);
                return ResponseEntity.ok(json);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting to JSON");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in session");
        }
    }

    @GetMapping("user/find-by-email/{email}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String email) {
        System.out.println(email);
        Map<String, Object> response = new HashMap<>();
        Users users = userService.findByEmail(email);
        response.put("users", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping("user/create-auth")
    public Users addUser(@RequestBody RegisterDto registerDto) {
        // send mail
        emailService.queueEmailRegister(registerDto);

        // mapper và lưu đối tượng user
        Users user = EntityDtoUtils.convertToEntity(registerDto, Users.class);
        return userService.register(user);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<Users> updateAccount(@PathVariable int userId, @RequestBody Users userEntity) {
        Users users = userService.findById(userId);
        users.setEmail(userEntity.getEmail());

        Users updateUser = userService.save(users);
        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("user/{userId}")
    public String deleteAccount(@PathVariable int userId) {
        userService.delete(userId);
        return "A record successfully deleted !";
    }
}

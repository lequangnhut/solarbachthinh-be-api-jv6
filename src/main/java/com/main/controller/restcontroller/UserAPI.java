package com.main.controller.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.dto.UsersDto;
import com.main.entity.Users;
import com.main.service.UserService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8080")
public class UserAPI {

    @Autowired
    UserService userService;

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
            usersDto.setProvince(users.getProvince());
            usersDto.setCity(users.getCity());
            usersDto.setWard(users.getWard());
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

    @PostMapping("user")
    public Users addUser(@RequestBody Users users) {
        users.setAcctive(Boolean.FALSE);
        return userService.save(users);
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

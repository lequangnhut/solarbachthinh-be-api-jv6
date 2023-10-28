package com.main.controller.restcontroller;

import com.main.entity.Users;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserAPI {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<Users> displayMessage() {
        return userService.findAllUser();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getAccountById(@PathVariable int userId) {
        Users users = userService.findById(userId);
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/user")
    public Users addUser(@RequestBody Users users) {
        users.setAcctive(Boolean.FALSE);
        return userService.save(users);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Users> updateAccount(@PathVariable int userId, @RequestBody Users userEntity) {
        Users users = userService.findById(userId);
        users.setEmail(userEntity.getEmail());

        Users updateUser = userService.save(users);
        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteAccount(@PathVariable int userId) {
        userService.delete(userId);
        return "A record successfully deleted !";
    }
}

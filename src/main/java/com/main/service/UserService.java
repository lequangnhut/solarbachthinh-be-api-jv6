package com.main.service;

import com.main.entity.Users;

import java.util.List;

public interface UserService {

    List<Users> findAllUser();

    Users findById(int userId);

    Users findByEmail(String email);

    Users findByToken(String token);

    Users save(Users users);

    Users update(Users users);

    Users delete(int userId);
}

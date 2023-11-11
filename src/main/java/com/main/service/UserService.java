package com.main.service;

import com.main.entity.Users;

import java.util.List;

public interface UserService {

    List<Users> findAllUser();

    List<Users> findByActiveIsTrue();

    Users findById(int userId);

    Users findByEmail(String email);

    Users findByPhoneNumber(String phoneNumber);

    Users findByToken(String token);

    Users register(Users users);

    Users save(Users users);

    Users update(Users users);

    Users delete(int userId);
}

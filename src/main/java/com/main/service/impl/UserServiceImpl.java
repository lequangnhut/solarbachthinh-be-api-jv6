package com.main.service.impl;

import com.main.entity.Roles;
import com.main.entity.Users;
import com.main.repository.RoleRepository;
import com.main.repository.UserRepository;
import com.main.service.UserService;
import com.main.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findByActiveIsTrue() {
        return userRepository.findByActiveIsTrue();
    }

    @Override
    public Users findById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Users findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public Users register(Users users) {
        users.setPasswords(passwordEncoder.encode(users.getPasswords()));
        users.setToken(RandomUtils.RandomToken(20));
        users.setDateCreated(new Timestamp(System.currentTimeMillis()));
        users.setAcctive(Boolean.FALSE);

        Roles role = roleRepository.findByNameRole("USER");
        if (role == null) {
            role = checkRoleExist();
        }
        users.setRoles(List.of(role));
        return userRepository.save(users);
    }

    @Override
    public Users save(Users users) {
        users.setPasswords(passwordEncoder.encode(users.getPasswords()));
        users.setToken(RandomUtils.RandomToken(20));
        users.setDateCreated(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(users);
    }

    @Override
    public Users update(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users delete(int userId) {
        Users users = findById(userId);
        if (users != null) {
            users.setAcctive(Boolean.FALSE);
        }
        return users;
    }

    private Roles checkRoleExist() {
        Roles role = new Roles();
        role.setNameRole("USER");
        return roleRepository.save(role);
    }
}

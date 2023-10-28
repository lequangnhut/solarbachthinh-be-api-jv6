package com.main.repository;

import com.main.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Users findByPhoneNumber(String phone);

    Users findByToken(String token);
}

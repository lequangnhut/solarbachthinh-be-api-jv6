package com.main.repository;

import com.main.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Users findByToken(String token);

    Users findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM Users u WHERE u.isAcctive = true")
    List<Users> findByActiveIsTrue();
}

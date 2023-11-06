package com.main.repository;

import com.main.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Users findByToken(String token);

    Users findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM Users u JOIN u.roles r  WHERE u.isAcctive = true AND r.nameRole LIKE 'USER' ORDER BY u.dateCreated DESC LIMIT 90")
    List<Users> findByActiveIsTrue();
}

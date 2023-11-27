package com.main.repository;

import com.main.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Users findUserByEmail(String email);

    Users findByToken(String token);

    Users findByPhoneNumber(String phoneNumber);

    Users findById(int userId);

    @Query("SELECT u FROM Users u JOIN u.roles r  WHERE  r.nameRole LIKE 'ROLE_USER' ORDER BY u.dateCreated DESC LIMIT 90")
    List<Users> findByActiveIsTrue();

    @Query("SELECT u.id FROM Users u WHERE u.phoneNumber = :phoneNumber AND u.id <> :currentUserId")
    Integer findIdByPhoneNumberAndNotCurrentUser(@Param("phoneNumber") String phoneNumber, @Param("currentUserId") Integer currentUserId);

    @Query("SELECT u FROM Users u JOIN u.roles r  WHERE u.isAcctive = true AND r.nameRole LIKE 'ROLE_USER' AND u.email = :email")
    Users findUserByRoleAndActive(String email);
}

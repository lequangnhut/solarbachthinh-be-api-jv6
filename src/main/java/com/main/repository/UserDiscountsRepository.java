package com.main.repository;

import com.main.entity.UserDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDiscountsRepository extends JpaRepository<UserDiscounts, Integer> {
}
package com.main.repository;

import com.main.entity.UserDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDiscountCodesRepository extends JpaRepository<UserDiscounts, Long> {
}
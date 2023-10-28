package com.main.repository;

import com.main.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discounts, String> {
}

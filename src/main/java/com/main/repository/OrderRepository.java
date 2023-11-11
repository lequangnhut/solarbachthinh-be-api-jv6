package com.main.repository;

import com.main.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findByUserIdOrderByDateCreatedDesc(int userId);
}
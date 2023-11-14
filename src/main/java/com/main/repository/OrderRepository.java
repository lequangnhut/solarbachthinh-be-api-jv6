package com.main.repository;

import com.main.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findByUserIdOrderByDateCreatedDesc(int userId);

    @Query("SELECT CalculateTotalOrderPrice(:userId)")
    BigDecimal sumOrdersPriceByAccountId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(o) FROM Orders o JOIN o.usersByUserId a WHERE a.id = :userId")
    BigDecimal countOrdersByAccountId(@Param("userId") Integer userId);
}
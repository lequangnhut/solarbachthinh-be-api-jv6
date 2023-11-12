package com.main.repository;

import com.main.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findByUserIdOrderByDateCreatedDesc(int userId);

    @Query("SELECT SUM(ct.price * ct.quantity + o.orderShipCost) FROM Orders o JOIN o.orderItemsById ct JOIN o.usersByUserId a WHERE a.id = :userId")
    Integer sumOrdersPriceByAccountId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(o) FROM Orders o JOIN o.usersByUserId a WHERE a.id = :userId")
    Integer countOrdersByAccountId(@Param("userId") Integer userId);
}
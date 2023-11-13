package com.main.service;

import com.main.entity.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> findByUserId(int userId);

    List<Orders> findAll();

    Orders findByOrderId(String orderId);

    Orders save(Orders orders);

    Integer sumOrderPrice(Integer userId);

    Integer countOrdersByAccountId(Integer userId);
}

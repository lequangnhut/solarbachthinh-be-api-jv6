package com.main.service;

import com.main.entity.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> findByUserId(int userId);

    Orders findByOrderId(String orderId);

    Orders save(Orders orders);
}

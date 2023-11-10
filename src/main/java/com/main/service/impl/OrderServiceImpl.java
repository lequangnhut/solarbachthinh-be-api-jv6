package com.main.service.impl;

import com.main.entity.Orders;
import com.main.repository.OrderRepository;
import com.main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
}

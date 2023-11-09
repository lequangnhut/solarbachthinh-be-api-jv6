package com.main.service.impl;

import com.main.entity.OrderItems;
import com.main.repository.OrderItemsRepository;
import com.main.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItems save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }
}

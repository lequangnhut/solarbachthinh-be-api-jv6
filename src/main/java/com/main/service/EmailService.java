package com.main.service;

import com.main.dto.OrdersDto;
import com.main.dto.RegisterDto;

public interface EmailService {

    void sendMailRegister();

    void queueEmailRegister(RegisterDto registerDto);

    void sendMailCreateOrder();

    void queueMailCreateOrder(OrdersDto ordersDto);
}

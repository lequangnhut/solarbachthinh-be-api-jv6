package com.main.service;

import com.main.dto.DiscountsDto;
import com.main.dto.OrdersDto;
import com.main.dto.PasswordsDto;
import com.main.dto.RegisterDto;

public interface EmailService {

    void sendMailRegister();

    void queueEmailRegister(RegisterDto registerDto);

    void sendMailCreateOrder();

    void queueMailCreateOrder(OrdersDto ordersDto);

    void sendMailForgot();

    void queueEmailForgot(PasswordsDto passwordsDto);

    void sendMailNotice();

    void queueEmailNotice(DiscountsDto discountsDto);
}

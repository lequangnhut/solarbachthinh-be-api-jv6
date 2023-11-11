package com.main.service;

import com.main.dto.RegisterDto;
import com.main.dto.UsersDto;

public interface EmailService {

    void queueEmailRegister(RegisterDto registerDto);

    void sendMailRegister();

//    void queueForgotEmail(ForgotPassDTO mail);
//
//    void sendMailForgotPass();
//
//    void queueMailPayment(PaymentDTO mail);
//
//    void sendMailPayment();

}

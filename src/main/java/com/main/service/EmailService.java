package com.main.service;

import com.main.dto.UsersDto;

public interface EmailService {

    void queueEmailRegister(UsersDto usersDto);

    void sendMailRegister();

//    void queueForgotEmail(ForgotPassDTO mail);
//
//    void sendMailForgotPass();
//
//    void queueMailPayment(PaymentDTO mail);
//
//    void sendMailPayment();

}

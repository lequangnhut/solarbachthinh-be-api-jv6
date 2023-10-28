package com.main.service.impl;

import com.main.dto.UsersDto;
import com.main.entity.Users;
import com.main.service.EmailService;
import com.main.service.ThymeleafService;
import com.main.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@EnableScheduling
public class EmailServiceImpl implements EmailService {

    Queue<UsersDto> emailQueue = new LinkedList<>();

    @Autowired
    JavaMailSender sender;

    @Autowired
    ThymeleafService thymeleafService;

    @Autowired
    UserService userService;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void queueEmailRegister(UsersDto usersDto) {
        emailQueue.add(usersDto);
    }

    @Override
    public void sendMailRegister() {
        while (!emailQueue.isEmpty()) {
            UsersDto usersDto = emailQueue.poll();
            Users users = userService.findByEmail(usersDto.getEmail());
            try {
                MimeMessage message = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

                helper.setTo(usersDto.getEmail());

                Map<String, Object> variables = new HashMap<>();
                variables.put("email", usersDto.getEmail());
                variables.put("password", usersDto.getPasswords());
                variables.put("full_name", usersDto.getFullname());
                variables.put("phone_number", usersDto.getPhoneNumber());
                variables.put("token", users.getToken());
                SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
                variables.put("date", sdfdate.format(new Date()));
                variables.put("time", sdftime.format(new Date()));

                helper.setFrom(email);
                helper.setText(thymeleafService.createContent("verify-email", variables), true);
                helper.setSubject("SOLAR BÁCH THỊNH XIN CHÂN THÀNH CẢM ƠN QUÝ KHÁCH HÀNG");

                sender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void processRegister() {
        sendMailRegister();
    }
}

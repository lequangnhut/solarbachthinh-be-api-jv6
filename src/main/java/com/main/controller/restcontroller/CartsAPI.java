package com.main.controller.restcontroller;

import com.main.entity.Carts;
import com.main.entity.Users;
import com.main.service.CartService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api")
public class CartsAPI {

    @Autowired
    CartService cartService;

    @Autowired
    HttpSession session;

    @GetMapping("carts")
    public List<Carts> getCarts() {
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
        return cartService.findListCartByUserId(users.getId());
    }
}

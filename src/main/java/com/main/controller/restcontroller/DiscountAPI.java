package com.main.controller.restcontroller;

import com.main.entity.Discounts;
import com.main.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api")
public class DiscountAPI {

    @Autowired
    DiscountService discountService;

    @GetMapping("discount")
    public List<Discounts> findAllDiscount() {
        return discountService.findAll();
    }

    @GetMapping("discount/{discount_code}")
    public Discounts findDiscountByDiscountCode(@PathVariable String discount_code) {
        return discountService.findById(discount_code);
    }
}

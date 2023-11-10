package com.main.service;

import com.main.entity.UserDiscounts;

import java.util.List;

public interface UserDiscountService {

    List<UserDiscounts> findAll();

    UserDiscounts save(UserDiscounts userDiscounts);
}

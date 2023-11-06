package com.main.service;

import com.main.entity.UserDiscounts;
import java.util.List;

public interface AccountDiscountCodeService {

    List<UserDiscounts> findAll();
}

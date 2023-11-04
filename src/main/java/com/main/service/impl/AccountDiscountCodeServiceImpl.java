package com.main.service.impl;

import com.main.entity.UserDiscounts;
import com.main.repository.AccountDiscountCodesRepository;
import com.main.service.AccountDiscountCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDiscountCodeServiceImpl implements AccountDiscountCodeService {
    private final AccountDiscountCodesRepository repo;

    public AccountDiscountCodeServiceImpl(AccountDiscountCodesRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<UserDiscounts> findAll() {
        return repo.findAll();
    }
}

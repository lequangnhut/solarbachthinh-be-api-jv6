package com.main.service;

import com.main.entity.HistoryUpdate;
import com.main.entity.Users;

import java.util.List;

public interface HistoryService {

    List<HistoryUpdate> findAll();

    void addHistoryUpdateProducts(Users users, String active);
}

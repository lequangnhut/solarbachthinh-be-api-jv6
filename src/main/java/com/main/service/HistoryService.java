package com.main.service;

import com.main.entity.HistoryUpdate;
import com.main.entity.Users;

import java.util.List;

public interface HistoryService {

    List<HistoryUpdate> findAll();

    void addHistory(String active);
}

package com.main.service.impl;

import com.main.entity.HistoryUpdate;
import com.main.entity.Users;
import com.main.repository.HistoryUpdateRepository;
import com.main.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryUpdateRepository repo;

    @Override
    public List<HistoryUpdate> findAll() {
        return repo.findAllByOrderByDateCreatedDesc();
    }

    public void addHistoryUpdateProducts(Users users, String active) {
        HistoryUpdate historyUpdate = new HistoryUpdate();
        historyUpdate.setUserId(users.getId());
        historyUpdate.setDateCreated(new Timestamp(System.currentTimeMillis()));
        historyUpdate.setActivity(active);
        repo.save(historyUpdate);
    }
}

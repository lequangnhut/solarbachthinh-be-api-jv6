package com.main.service.impl;

import com.main.entity.HistoryUpdate;
import com.main.entity.Users;
import com.main.repository.HistoryUpdateRepository;
import com.main.service.HistoryService;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryUpdateRepository repo;

    private final HttpSession session;

    // Constructor để inject HttpSession
    @Autowired
    public HistoryServiceImpl(HttpSession session) {
        this.session = session;
    }

    @Override
    public List<HistoryUpdate> findAll() {
        return repo.findAllByOrderByDateCreatedDesc();
    }

    public void addHistory(String active) {
        // Truy cập session trong phương thức
        Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);

        HistoryUpdate historyUpdate = new HistoryUpdate();
        historyUpdate.setUserId(users.getId());
        historyUpdate.setDateCreated(new Timestamp(System.currentTimeMillis()));
        historyUpdate.setActivity(active);
        repo.save(historyUpdate);
    }
}

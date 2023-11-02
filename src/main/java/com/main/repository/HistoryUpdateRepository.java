package com.main.repository;

import com.main.entity.HistoryUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryUpdateRepository extends JpaRepository<HistoryUpdate, Integer> {

    List<HistoryUpdate> findAllByOrderByDateCreatedDesc();
}

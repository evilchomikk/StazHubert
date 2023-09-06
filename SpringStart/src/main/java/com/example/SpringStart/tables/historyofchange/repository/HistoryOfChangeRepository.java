package com.example.SpringStart.tables.historyofchange.repository;

import com.example.SpringStart.tables.historyofchange.model.HistoryOfChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryOfChangeRepository extends JpaRepository<HistoryOfChange, Integer> {
}

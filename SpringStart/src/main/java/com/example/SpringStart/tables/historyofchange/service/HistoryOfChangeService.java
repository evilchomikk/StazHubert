package com.example.SpringStart.tables.historyofchange.service;

import com.example.SpringStart.tables.historyofchange.model.HistoryOfChangeDTO;

import java.util.List;

public interface HistoryOfChangeService {

    void addHistoryOfChange(HistoryOfChangeDTO historyOfChangeDto);

    List<HistoryOfChangeDTO> getHistoryOfChanges();
}

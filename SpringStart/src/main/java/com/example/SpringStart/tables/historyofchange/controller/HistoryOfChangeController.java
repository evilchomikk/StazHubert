package com.example.SpringStart.tables.historyofchange.controller;

import com.example.SpringStart.commons.dto.historyofchange.HistoryOfChangeDTO;
import com.example.SpringStart.tables.historyofchange.service.HistoryOfChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historyofchange")
public class HistoryOfChangeController {

    private final HistoryOfChangeService historyOfChangeService;

    @PostMapping
    private void addHistoryOfChange(@RequestBody HistoryOfChangeDTO historyOfChangeDto) {
        historyOfChangeService.addHistoryOfChange(historyOfChangeDto);
    }

    @GetMapping("/get")
    public List<HistoryOfChangeDTO> getHistoryOfChanges() {
        return historyOfChangeService.getHistoryOfChanges();
    }

}

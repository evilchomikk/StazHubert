package com.example.SpringStart.tables.historyofchange.service;

import com.example.SpringStart.tables.historyofchange.repository.HistoryOfChangeRepository;
import com.example.SpringStart.tables.historyofchange.mapper.HistoryOfChangeMapper;
import com.example.SpringStart.commons.dto.historyofchange.HistoryOfChangeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class HistoryOfChangeServiceImpl implements HistoryOfChangeService {

    private final HistoryOfChangeRepository historyOfChangeRepository;
    private final HistoryOfChangeMapper historyOfChangeMapper;

    @Override
    public void addHistoryOfChange(HistoryOfChangeDTO historyOfChangeDto) {
        historyOfChangeRepository.save(historyOfChangeMapper.dtoToEntity(historyOfChangeDto));
    }

    @Override
    public List<HistoryOfChangeDTO> getHistoryOfChanges() {
        return historyOfChangeMapper.entityToDto(historyOfChangeRepository.findAll());
    }

}

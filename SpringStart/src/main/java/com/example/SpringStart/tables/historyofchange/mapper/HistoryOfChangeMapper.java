package com.example.SpringStart.tables.historyofchange.mapper;

import com.example.SpringStart.tables.historyofchange.model.HistoryOfChange;
import com.example.SpringStart.commons.dto.historyofchange.HistoryOfChangeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface HistoryOfChangeMapper {

    HistoryOfChange dtoToEntity(HistoryOfChangeDTO historyOfChange);

    HistoryOfChangeDTO entityToDto(HistoryOfChange historyOfChange);

    List<HistoryOfChangeDTO> entityToDto(List<HistoryOfChange> historyOfChange);

    List<HistoryOfChange> dtoToEntity(List<HistoryOfChangeDTO> historyOfChange);
}

package com.example.SpringStart.tables.logindata.mapper;

import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.commons.dto.logindata.LoginDataDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LoginDataMapper {

    LoginData dtoToEntity(LoginDataDTO loginDataDto);

    LoginDataDTO entityToDto(LoginData loginData);

    List<LoginDataDTO> entityToDto(List<LoginData> loginData);

    List<LoginData> dtoToEntity(List<LoginDataDTO> loginDataDto);
}

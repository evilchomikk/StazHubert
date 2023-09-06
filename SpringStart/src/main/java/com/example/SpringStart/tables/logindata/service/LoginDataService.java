package com.example.SpringStart.tables.logindata.service;

import com.example.SpringStart.commons.dto.logindata.LoginDataDTO;

import java.util.List;

public interface LoginDataService {

    String addLoginData(LoginDataDTO loginDataDto);

    List<LoginDataDTO> getLoginData();

    LoginDataDTO getLoginData(Integer id);
}

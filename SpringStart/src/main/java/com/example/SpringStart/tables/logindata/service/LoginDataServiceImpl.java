package com.example.SpringStart.tables.logindata.service;

import com.example.SpringStart.tables.logindata.repository.LoginDataRepository;
import com.example.SpringStart.tables.logindata.mapper.LoginDataMapper;
import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.tables.logindata.model.LoginDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class LoginDataServiceImpl implements LoginDataService {

    private final LoginDataRepository loginDataRepository;
    private final LoginDataMapper loginDataMapper;

    @Override
    public String addLoginData(LoginDataDTO loginDataDto) {
        loginDataRepository.save(loginDataMapper.dtoToEntity(loginDataDto));
        LoginData loginDataFromRep = loginDataRepository.findAll().stream()
                .filter(loginDataRep -> loginDataRep.getLogin().equals(loginDataDto.getLogin()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("LoginData not found"));

        return String.format("LoginData with id %d added", loginDataFromRep.getId());
    }

    @Override
    public List<LoginDataDTO> getLoginData() {
        return loginDataMapper.entityToDto(loginDataRepository.findAll());
    }

    @Override
    public LoginDataDTO getLoginData(Integer id) {
        return loginDataMapper.entityToDto(loginDataRepository.findById(id).orElseThrow());
    }

}

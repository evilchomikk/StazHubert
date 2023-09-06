package com.example.SpringStart.tables.logindata.controller;

import com.example.SpringStart.tables.logindata.model.LoginDataDTO;
import com.example.SpringStart.tables.logindata.service.LoginDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/logindata")
public class LoginDataController {

    private final LoginDataService loginDataService;

    @PostMapping("/add")
    private String addLoginData(@RequestBody LoginDataDTO loginDataDto) {
        return loginDataService.addLoginData(loginDataDto);
    }

    @GetMapping("/get")
    private List<LoginDataDTO> getLoginData() {
        return loginDataService.getLoginData();
    }

    @GetMapping("/get/{id}")
    private LoginDataDTO getLoginData(@PathVariable Integer id) {
        return loginDataService.getLoginData(id);
    }
}

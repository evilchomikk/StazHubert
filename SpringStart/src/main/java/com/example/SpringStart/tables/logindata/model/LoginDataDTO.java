package com.example.SpringStart.tables.logindata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDataDTO {

    private Integer id;
    private String login;
    private String password;
}

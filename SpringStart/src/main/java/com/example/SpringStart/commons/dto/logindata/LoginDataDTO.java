package com.example.SpringStart.commons.dto.logindata;

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

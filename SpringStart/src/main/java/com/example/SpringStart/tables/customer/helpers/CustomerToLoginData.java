package com.example.SpringStart.tables.customer.helpers;

import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.commons.dto.logindata.LoginDataDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerToLoginData {
    CustomerDTO customerDto;
    LoginDataDTO loginDataDto;
}

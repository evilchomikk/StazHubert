package com.example.SpringStart.tables.customer.helpers;

import com.example.SpringStart.tables.customer.model.CustomerDTO;
import com.example.SpringStart.tables.logindata.model.LoginDataDTO;
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

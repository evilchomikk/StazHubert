package com.example.SpringStart.commons.dto.customer;

import com.example.SpringStart.tables.embedded.fileds.Name;
import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.tables.orders.model.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDTO {

    private Integer id;
    private Name name;
    private LoginData loginData;
    private List<Orders> orders;
}

package com.example.SpringStart.tables.customer.service;

import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.tables.customer.helpers.CustomerToLoginData;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerToLoginData customerToLoginData);

    List<CustomerDTO> getCustomers();
}

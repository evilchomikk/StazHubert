package com.example.SpringStart.tables.customer.controller;

import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.tables.customer.helpers.CustomerToLoginData;
import com.example.SpringStart.tables.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerToLoginData customerToLoginData) {
        return customerService.addCustomer(customerToLoginData);
    }

    @GetMapping("/get")
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }
}

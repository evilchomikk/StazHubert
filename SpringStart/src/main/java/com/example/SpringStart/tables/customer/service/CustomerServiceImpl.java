package com.example.SpringStart.tables.customer.service;

import com.example.SpringStart.tables.customer.repository.CustomerRepository;
import com.example.SpringStart.tables.customer.mapper.CustomerMapper;
import com.example.SpringStart.tables.customer.model.Customer;
import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.tables.customer.helpers.CustomerToLoginData;
import com.example.SpringStart.tables.logindata.mapper.LoginDataMapper;
import com.example.SpringStart.commons.dto.logindata.LoginDataDTO;
import com.example.SpringStart.tables.logindata.service.LoginDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final LoginDataService loginDataService;
    private final CustomerMapper customerMapper;
    private final LoginDataMapper loginDataMapper;

    @Override
    public String addCustomer(CustomerToLoginData customerToLoginData) {

        CustomerDTO customer = customerToLoginData.getCustomerDto();
        LoginDataDTO loginData = customerToLoginData.getLoginDataDto();

        customer.setLoginData(loginDataMapper.dtoToEntity(loginData));
        loginDataService.addLoginData(loginData);
        customerRepository.save(customerMapper.dtoToEntity(customer));

        Customer customerFromRep = customerRepository.findAll().stream()
                .filter(customerRep -> customerRep.getName().equals(customer.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return String.format("Customer with id %d added", customerFromRep.getId());
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerMapper.entityToDto(customerRepository.findAll());
    }
}

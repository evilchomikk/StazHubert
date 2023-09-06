package com.example.SpringStart.tables.customer.mapper;

import com.example.SpringStart.tables.customer.model.Customer;
import com.example.SpringStart.tables.customer.model.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToEntity(CustomerDTO customerDTO);

    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> entityToDto(List<Customer> customer);

    List<Customer> dtoToEntity(List<CustomerDTO> customerDTO);
}

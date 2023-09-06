package com.example.SpringStart.tables.address.model;

import com.example.SpringStart.tables.customer.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private Integer id;
    private String street;
    private String city;
    private String ZipCode;
    private boolean BaseAddress;
    private Customer customer;
}

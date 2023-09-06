package com.example.SpringStart.tables.cart.model;

import com.example.SpringStart.tables.customer.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

    private Integer id;
    private Customer customer;
}

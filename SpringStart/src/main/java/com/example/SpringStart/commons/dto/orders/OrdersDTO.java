package com.example.SpringStart.commons.dto.orders;

import com.example.SpringStart.tables.customer.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO {

    private Integer id;
    private Customer customer;
    private LocalDate dateOfOrder;
}

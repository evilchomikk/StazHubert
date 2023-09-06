package com.example.SpringStart.tables.historyofchange.model;

import com.example.SpringStart.tables.employee.model.Employee;
import com.example.SpringStart.tables.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HistoryOfChangeDTO {

    private Integer id;
    private Product product;
    private float oldPrice;
    private float newPrice;
    private LocalDate dateOfChange;
    private Employee employee;
}

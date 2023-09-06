package com.example.SpringStart.tables.ordersproduct.model;

import com.example.SpringStart.tables.embedded.id.OrdersProductId;
import com.example.SpringStart.tables.orders.model.Orders;
import com.example.SpringStart.tables.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersProductDTO {

    private OrdersProductId id;
    private Product product;
    private Orders order;
    private int ammount;
    private float price;
}

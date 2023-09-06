package com.example.SpringStart.tables.orders.service;

import com.example.SpringStart.tables.orders.model.OrdersDTO;

import java.util.List;

public interface OrdersService {

    void addOrders(OrdersDTO orders);

    List<OrdersDTO> getOrders();
}

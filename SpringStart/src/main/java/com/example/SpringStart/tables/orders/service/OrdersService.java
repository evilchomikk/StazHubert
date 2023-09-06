package com.example.SpringStart.tables.orders.service;

import com.example.SpringStart.commons.dto.orders.OrdersDTO;

import java.util.List;

public interface OrdersService {

    void addOrders(OrdersDTO orders);

    List<OrdersDTO> getOrders();
}

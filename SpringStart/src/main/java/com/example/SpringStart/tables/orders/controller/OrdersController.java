package com.example.SpringStart.tables.orders.controller;

import com.example.SpringStart.tables.orders.model.OrdersDTO;
import com.example.SpringStart.tables.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/add")
    private void addOrders(@RequestBody OrdersDTO ordersDto) {
        ordersService.addOrders(ordersDto);
    }

    @GetMapping("/get")
    public List<OrdersDTO> getOrders() {
        return ordersService.getOrders();
    }
}

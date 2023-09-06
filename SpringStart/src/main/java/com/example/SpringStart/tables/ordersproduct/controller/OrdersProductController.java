package com.example.SpringStart.tables.ordersproduct.controller;

import com.example.SpringStart.tables.ordersproduct.service.OrdersProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ordersproduct")
public class OrdersProductController {
    private final OrdersProductService ordersProductService;
}

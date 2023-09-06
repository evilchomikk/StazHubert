package com.example.SpringStart.tables.cartproduct.controller;

import com.example.SpringStart.tables.cartproduct.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartProduct")
public class CartProductController {

    private final CartProductService cartProductService;

}

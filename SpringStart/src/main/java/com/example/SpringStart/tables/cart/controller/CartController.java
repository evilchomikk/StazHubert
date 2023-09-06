package com.example.SpringStart.tables.cart.controller;

import com.example.SpringStart.commons.dto.cart.CartDTO;
import com.example.SpringStart.tables.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public void addCart(@RequestBody CartDTO cartDTO) {
        cartService.addCart(cartDTO);
    }

    @GetMapping("/get")
    public void getCarts() {
        cartService.getCarts();
    }
}

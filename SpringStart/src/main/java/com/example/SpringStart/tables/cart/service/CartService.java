package com.example.SpringStart.tables.cart.service;

import com.example.SpringStart.tables.cart.model.CartDTO;

public interface CartService {

    void addCart(CartDTO cartDto);

    void getCarts();
}

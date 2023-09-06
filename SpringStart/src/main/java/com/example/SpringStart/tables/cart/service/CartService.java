package com.example.SpringStart.tables.cart.service;

import com.example.SpringStart.commons.dto.cart.CartDTO;

public interface CartService {

    void addCart(CartDTO cartDto);

    void getCarts();
}

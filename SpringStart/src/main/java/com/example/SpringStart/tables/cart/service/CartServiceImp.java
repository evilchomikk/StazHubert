package com.example.SpringStart.tables.cart.service;

import com.example.SpringStart.tables.cart.repository.CartRepository;
import com.example.SpringStart.tables.cart.mapper.CartMapper;
import com.example.SpringStart.tables.cart.model.CartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public void addCart(CartDTO cartDTO) {
        cartRepository.save(cartMapper.dtoToEntity(cartDTO));
    }

    @Override
    public void getCarts() {
        cartRepository.findAll();
    }
}

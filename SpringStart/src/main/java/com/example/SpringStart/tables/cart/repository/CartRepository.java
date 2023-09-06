package com.example.SpringStart.tables.cart.repository;

import com.example.SpringStart.tables.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}

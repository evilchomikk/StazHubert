package com.example.SpringStart.tables.cartproduct.repository;

import com.example.SpringStart.tables.cartproduct.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}

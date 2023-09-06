package com.example.SpringStart.tables.ordersproduct.repository;

import com.example.SpringStart.tables.ordersproduct.model.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Long> {
}

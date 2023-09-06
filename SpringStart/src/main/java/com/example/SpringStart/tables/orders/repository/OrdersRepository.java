package com.example.SpringStart.tables.orders.repository;

import com.example.SpringStart.tables.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}

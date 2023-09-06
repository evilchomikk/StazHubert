package com.example.SpringStart.tables.customer.repository;

import com.example.SpringStart.tables.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

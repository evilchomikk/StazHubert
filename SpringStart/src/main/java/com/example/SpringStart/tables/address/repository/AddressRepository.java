package com.example.SpringStart.tables.address.repository;

import com.example.SpringStart.tables.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

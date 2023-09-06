package com.example.SpringStart.tables.address.service;

import com.example.SpringStart.tables.address.model.AddressDTO;

import java.util.List;

public interface AddressService {
    void addAddress(AddressDTO addressDTO);

    List<AddressDTO> getAddresses();
}

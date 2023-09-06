package com.example.SpringStart.tables.address.controller;

import com.example.SpringStart.commons.dto.address.AddressDTO;
import com.example.SpringStart.tables.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    private void addAddress(@RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
    }

    @GetMapping("/get")
    private List<AddressDTO> getAddress() {
        return addressService.getAddresses();
    }
}

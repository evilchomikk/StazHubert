package com.example.SpringStart.tables.address.service;

import com.example.SpringStart.tables.address.repository.AddressRepository;
import com.example.SpringStart.tables.address.mapper.AddressMapper;
import com.example.SpringStart.tables.address.model.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public void addAddress(AddressDTO addressDTO) {
        addressRepository.save(addressMapper.dtoToEntity(addressDTO));
    }

    @Override
    public List<AddressDTO> getAddresses() {
        return addressMapper.entityToDto(addressRepository.findAll());
    }

}

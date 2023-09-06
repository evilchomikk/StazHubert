package com.example.SpringStart.tables.address.mapper;

import com.example.SpringStart.tables.address.model.Address;
import com.example.SpringStart.commons.dto.address.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address dtoToEntity(AddressDTO addressDTO);

    AddressDTO entityToDto(Address address);

    List<AddressDTO> entityToDto(List<Address> address);

    List<Address> dtoToEntity(List<AddressDTO> addressDTO);
}

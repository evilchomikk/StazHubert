package com.example.SpringStart.tables.cartproduct.mapper;

import com.example.SpringStart.tables.cartproduct.model.CartProduct;
import com.example.SpringStart.commons.dto.cartproduct.CartProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartProductMapper {

    CartProduct dtoToEntity(CartProductDTO cartProductDTO);

    CartProductDTO entityToDto(CartProduct cartProduct);

    List<CartProduct> dtoToEntity(List<CartProductDTO> cartProductDTO);

    List<CartProductDTO> entityToDto(List<CartProduct> cartProduct);

}

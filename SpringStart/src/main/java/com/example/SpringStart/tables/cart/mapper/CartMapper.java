package com.example.SpringStart.tables.cart.mapper;

import com.example.SpringStart.tables.cart.model.Cart;
import com.example.SpringStart.commons.dto.cart.CartDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO entityToDto(Cart cart);

    Cart dtoToEntity(CartDTO cartDTO);

    List<CartDTO> entityToDto(List<Cart> cartList);

    List<Cart> dtoToEntity(List<CartDTO> cartDTOList);
}

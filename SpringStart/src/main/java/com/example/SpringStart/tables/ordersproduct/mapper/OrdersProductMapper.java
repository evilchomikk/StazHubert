package com.example.SpringStart.tables.ordersproduct.mapper;

import com.example.SpringStart.tables.ordersproduct.model.OrdersProduct;
import com.example.SpringStart.tables.ordersproduct.model.OrdersProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersProductMapper {

    OrdersProduct dtoToEntity(OrdersProductDTO ordersProductDto);

    OrdersProductDTO entityToDto(OrdersProduct ordersProduct);

    List<OrdersProduct> dtoToEntity(List<OrdersProductDTO> ordersProductDto);

    List<OrdersProductDTO> entityToDto(List<OrdersProduct> ordersProduct);
}

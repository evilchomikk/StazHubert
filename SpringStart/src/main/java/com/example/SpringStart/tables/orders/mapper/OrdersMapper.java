package com.example.SpringStart.tables.orders.mapper;

import com.example.SpringStart.tables.orders.model.Orders;
import com.example.SpringStart.tables.orders.model.OrdersDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    Orders dtoToEntity(OrdersDTO ordersDto);

    OrdersDTO entityToDto(Orders orders);

    List<OrdersDTO> entityToDto(List<Orders> orders);

    List<Orders> dtoToEntity(List<OrdersDTO> ordersDto);
}

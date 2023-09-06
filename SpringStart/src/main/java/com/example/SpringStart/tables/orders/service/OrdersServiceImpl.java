package com.example.SpringStart.tables.orders.service;

import com.example.SpringStart.tables.orders.repository.OrdersRepository;
import com.example.SpringStart.tables.orders.mapper.OrdersMapper;
import com.example.SpringStart.commons.dto.orders.OrdersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    @Override
    public void addOrders(OrdersDTO ordersDto) {
        ordersRepository.save(ordersMapper.dtoToEntity(ordersDto));
    }

    @Override
    public List<OrdersDTO> getOrders() {
        return ordersMapper.entityToDto(ordersRepository.findAll());
    }
}

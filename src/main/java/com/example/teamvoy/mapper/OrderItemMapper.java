package com.example.teamvoy.mapper;

import com.example.teamvoy.domain.OrderItem;
import com.example.teamvoy.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper implements Mapper<OrderItem, OrderItemDTO> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public OrderItemDTO toDto(OrderItem orderItem, List<String> args) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .order(orderMapper.toDto(orderItem.getOrder()))
                .item(itemMapper.toDto(orderItem.getItem()))
                .build();
    }

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO, List<String> args) {
        return null;
    }
}

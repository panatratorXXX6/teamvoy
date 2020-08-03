package com.example.teamvoy.mapper;

import com.example.teamvoy.domain.Item;
import com.example.teamvoy.domain.Order;
import com.example.teamvoy.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements Mapper<Order, OrderDTO> {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public OrderDTO toDto(Order order, List<String> args) {
        List<Item> items = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            items.add(orderItem.getItem());
        });

        return OrderDTO.builder()
                .id(order.getId())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .createdDate(order.getCreatedDate().toEpochMilli())
                .expirationDate(order.getExpirationDate().toEpochMilli())
                .items(itemMapper.toDto(items))
                .build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO, List<String> args) {
        return Order.builder()
                .id(orderDTO.getId())
                .price(orderDTO.getPrice())
                .quantity(orderDTO.getQuantity())
                .createdDate(Instant.ofEpochSecond(orderDTO.getCreatedDate()))
                .expirationDate(Instant.ofEpochSecond(orderDTO.getExpirationDate()))
                .build();
    }
}

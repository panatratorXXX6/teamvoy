package com.example.teamvoy.service;

import com.example.teamvoy.dto.CreateOrderDTO;
import com.example.teamvoy.dto.OrderDTO;
import com.example.teamvoy.dto.UpdateOrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Long createOrder(CreateOrderDTO createOrderDTO);

    OrderDTO updateOrder(Long orderId, UpdateOrderDTO updateOrderDTO);

    OrderDTO getOrder(Long orderId);

    List<OrderDTO> getOrders(Pageable pageable);

    boolean deleteOrder(Long orderId);
}

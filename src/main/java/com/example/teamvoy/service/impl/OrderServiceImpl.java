package com.example.teamvoy.service.impl;

import com.example.teamvoy.domain.Item;
import com.example.teamvoy.domain.Order;
import com.example.teamvoy.domain.OrderItem;
import com.example.teamvoy.dto.CreateOrderDTO;
import com.example.teamvoy.dto.OrderDTO;
import com.example.teamvoy.dto.UpdateOrderDTO;
import com.example.teamvoy.exceptions.OrderNotFoundException;
import com.example.teamvoy.mapper.OrderMapper;
import com.example.teamvoy.repository.ItemRepository;
import com.example.teamvoy.repository.OrderItemRepository;
import com.example.teamvoy.repository.OrderRepository;
import com.example.teamvoy.service.OrderService;
import com.example.teamvoy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Value("${validity-in-seconds}")
    private Long expirationTime;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Long createOrder(CreateOrderDTO createOrderDTO) {
        List<Item> items = itemRepository.findAllById(createOrderDTO.getItemIds());

        Order order = orderRepository.save(
                Order.builder()
                        .price(createOrderDTO.getPrice())
                        .createdDate(Instant.now())
                        .expirationDate(Instant.now().plusSeconds(expirationTime))
                        .quantity(createOrderDTO.getQuantity())
                        .build()
        );

        if (!StringUtils.isEmpty(items)) {
            items.forEach(item -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setOrder(order);

                orderItemRepository.save(orderItem);
            });
        }

        return order.getId();
    }

    @Override
    public OrderDTO updateOrder(Long orderId, UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(orderId).orElse(null);
        List<Item> items = itemRepository.findAllById(updateOrderDTO.getItemIds());

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (!StringUtils.isEmpty(updateOrderDTO.getItemIds())) {
            orderItemRepository.deleteAllByOrderId(order.getId());

            List<OrderItem> orderItems = new ArrayList<>();
            items.forEach(item -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setOrder(order);

                orderItems.add(orderItemRepository.save(orderItem));
            });

            order.setOrderItems(orderItems);
        }

        CommonUtil.setIfNotNull(updateOrderDTO::getPrice, order::setPrice);
        CommonUtil.setIfNotNull(updateOrderDTO::getQuantity, order::setQuantity);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDTO> getOrders(Pageable pageable) {
        return orderMapper.toDto(orderRepository.findAllByPageable(pageable));
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (!isOrderValid(order.getExpirationDate())) {
            orderItemRepository.deleteAllByOrderId(orderId);
            orderRepository.deleteById(orderId);

            return true;
        }
        
        return false;
    }

    private boolean isOrderValid(Instant expirationOrderDate) {
        return expirationOrderDate.compareTo(Instant.now()) >= 0;
    }

}

package com.example.teamvoy.controller;

import com.example.teamvoy.dto.CreateOrderDTO;
import com.example.teamvoy.dto.OrderDTO;
import com.example.teamvoy.dto.UpdateOrderDTO;
import com.example.teamvoy.service.OrderService;
import com.example.teamvoy.util.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public Long createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderDTO updateOrderDTO) {
        return orderService.updateOrder(orderId, updateOrderDTO);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/")
    public List<OrderDTO> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }

    @DeleteMapping("/{orderId}")
    public DataWrapper deleteOrder(@PathVariable Long orderId) {
        return DataWrapper.of(orderService.deleteOrder(orderId));
    }

}

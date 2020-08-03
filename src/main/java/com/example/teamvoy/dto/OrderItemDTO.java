package com.example.teamvoy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {

    private Long id;
    private OrderDTO order;
    private ItemDTO item;

}

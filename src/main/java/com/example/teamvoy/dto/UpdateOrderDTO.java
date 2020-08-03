package com.example.teamvoy.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderDTO {

    private Double price;
    private Long quantity;
    private List<Long> itemIds;

}

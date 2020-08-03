package com.example.teamvoy.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Long id;
    private Double price;
    private Long quantity;
    private Long createdDate;
    private Long expirationDate;
    private List<ItemDTO> items;

}

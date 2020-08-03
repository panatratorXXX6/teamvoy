package com.example.teamvoy.dto;

import lombok.Data;

@Data
public class CreateItemDTO {

    private String imageUrl;
    private String name;
    private Double price;
    private Long quantity;

}

package com.example.teamvoy.dto;

import lombok.Data;

@Data
public class UpdateItemDTO {

    private String imageUrl;
    private String name;
    private Double price;
    private Long quantity;
    private Boolean isAvailable;

}

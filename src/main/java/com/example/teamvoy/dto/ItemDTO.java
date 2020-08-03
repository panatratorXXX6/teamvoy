package com.example.teamvoy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {

    private Long id;
    private String imageUrl;
    private String name;
    private Double price;
    private Long quantity;
    private Long createdDate;
    private Boolean isAvailable;

}

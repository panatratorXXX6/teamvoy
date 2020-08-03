package com.example.teamvoy.mapper;

import com.example.teamvoy.domain.Item;
import com.example.teamvoy.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class ItemMapper implements Mapper<Item, ItemDTO> {

    @Override
    public ItemDTO toDto(Item item, List<String> args) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .imageUrl(item.getImageUrl())
                .isAvailable(item.getIsAvailable())
                .quantity(item.getQuantity())
                .createdDate(item.getCreatedDate().toEpochMilli())
                .build();
    }

    @Override
    public Item toEntity(ItemDTO itemDTO, List<String> args) {
        return Item.builder()
                .id(itemDTO.getId())
                .name(itemDTO.getName())
                .price(itemDTO.getPrice())
                .imageUrl(itemDTO.getImageUrl())
                .isAvailable(itemDTO.getIsAvailable())
                .quantity(itemDTO.getQuantity())
                .createdDate(Instant.ofEpochSecond(itemDTO.getCreatedDate()))
                .build();
    }
}

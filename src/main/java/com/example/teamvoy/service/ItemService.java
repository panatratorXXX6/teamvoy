package com.example.teamvoy.service;

import com.example.teamvoy.dto.CreateItemDTO;
import com.example.teamvoy.dto.ItemDTO;
import com.example.teamvoy.dto.UpdateItemDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    Long createItem(CreateItemDTO createItemDTO);

    ItemDTO updateItem(Long itemId, UpdateItemDTO updateItemDTO);

    ItemDTO getItem(Long itemId);

    List<ItemDTO> getItems(Pageable pageable);

    boolean deleteItem(Long itemId);
}

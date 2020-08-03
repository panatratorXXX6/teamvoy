package com.example.teamvoy.controller;

import com.example.teamvoy.dto.CreateItemDTO;
import com.example.teamvoy.dto.ItemDTO;
import com.example.teamvoy.dto.UpdateItemDTO;
import com.example.teamvoy.service.ItemService;
import com.example.teamvoy.util.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/")
    public Long createItem(@RequestBody CreateItemDTO createItemDTO) {
        return itemService.createItem(createItemDTO);
    }

    @PutMapping("/{itemId}")
    public ItemDTO updateItem(@PathVariable Long itemId, @RequestBody UpdateItemDTO updateItemDTO) {
        return itemService.updateItem(itemId, updateItemDTO);
    }

    @GetMapping("/{itemId}")
    public ItemDTO getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }

    // to get items with the lowest price try : "/api/item/?sort=price,asc"
    @GetMapping("/")
    public List<ItemDTO> getItems(Pageable pageable) {
        return itemService.getItems(pageable);
    }

    @DeleteMapping("/{itemId}")
    public DataWrapper deleteItem(@PathVariable Long itemId) {
        return DataWrapper.of(itemService.deleteItem(itemId));
    }

}

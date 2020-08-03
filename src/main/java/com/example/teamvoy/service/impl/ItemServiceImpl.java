package com.example.teamvoy.service.impl;

import com.example.teamvoy.domain.Item;
import com.example.teamvoy.dto.CreateItemDTO;
import com.example.teamvoy.dto.ItemDTO;
import com.example.teamvoy.dto.UpdateItemDTO;
import com.example.teamvoy.exceptions.ItemNotFoundException;
import com.example.teamvoy.mapper.ItemMapper;
import com.example.teamvoy.repository.ItemRepository;
import com.example.teamvoy.repository.OrderItemRepository;
import com.example.teamvoy.service.ItemService;
import com.example.teamvoy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Long createItem(CreateItemDTO createItemDTO) {
        return itemRepository.save(
                Item.builder()
                .imageUrl(createItemDTO.getImageUrl())
                .quantity(createItemDTO.getQuantity())
                .price(createItemDTO.getPrice())
                .createdDate(Instant.now())
                .isAvailable(true)
                .build())
                .getId();
    }

    @Override
    public ItemDTO updateItem(Long itemId, UpdateItemDTO updateItemDTO) {
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item == null) {
            throw new ItemNotFoundException();
        }

        CommonUtil.setIfNotNull(updateItemDTO::getImageUrl, item::setImageUrl);
        CommonUtil.setIfNotNull(updateItemDTO::getName, item::setName);
        CommonUtil.setIfNotNull(updateItemDTO::getPrice, item::setPrice);
        CommonUtil.setIfNotNull(updateItemDTO::getQuantity, item::setQuantity);
        CommonUtil.setIfNotNull(updateItemDTO::getIsAvailable, item::setIsAvailable);

        return itemMapper.toDto(itemRepository.save(item));
    }

    @Override
    public ItemDTO getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item == null) {
            throw new ItemNotFoundException();
        }

        return itemMapper.toDto(item);
    }

    @Override
    public List<ItemDTO> getItems(Pageable pageable) {
        return itemMapper.toDto(itemRepository.findAllByPageable(pageable));
    }

    @Override
    public boolean deleteItem(Long itemId) {
        orderItemRepository.deleteAllByItemId(itemId);
        itemRepository.deleteById(itemId);
        return true;
    }
}

package com.sparta.spartagoods.item.service;

import com.sparta.spartagoods.item.dto.ItemCreateRequest;
import com.sparta.spartagoods.item.dto.ItemResponse;
import com.sparta.spartagoods.item.entity.Item;
import com.sparta.spartagoods.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemResponse saveItem(ItemCreateRequest request) {
        Item item = new Item(request);
        return new ItemResponse(itemRepository.save(item));
    }

    @Transactional(readOnly = true)
    public ItemResponse getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")
        );
        return new ItemResponse(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemResponse> getItems(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Item> items = itemRepository.findAllBy(pageable);
        Page<ItemResponse> itemList = items.map(ItemResponse::new);

        return itemList;
    }

    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품은 존재하지 않습니다.")
        );
    }
}

package com.sparta.spartagoods.item.controller;

import com.sparta.spartagoods.item.dto.ItemCreateRequest;
import com.sparta.spartagoods.item.dto.ItemResponse;
import com.sparta.spartagoods.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item/save")
    public ItemResponse saveItem(@RequestBody ItemCreateRequest request){
        return itemService.saveItem(request);
    }

    @GetMapping("/item/{itemId}")
    public ItemResponse getItem(@PathVariable Long itemId){
        return itemService.getItem(itemId);
    }

    @GetMapping("/items")
    public Page<ItemResponse> getItems(@RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       @RequestParam("sortBy") String sortBy,
                                       @RequestParam("isAsc") boolean isAsc){
        return itemService.getItems(page-1,size,sortBy,isAsc);
    }


}

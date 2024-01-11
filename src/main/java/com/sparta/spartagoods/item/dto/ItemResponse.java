package com.sparta.spartagoods.item.dto;

import com.sparta.spartagoods.item.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponse {

    private Long id;
    private String itemName;
    private int price;
    private int quantity;
    private String introduction;
    private String category;


    public ItemResponse(Item item) {
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.introduction = item.getIntroduction();
        this.category = item.getCategory();
    }
}

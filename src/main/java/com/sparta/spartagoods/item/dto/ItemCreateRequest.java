package com.sparta.spartagoods.item.dto;

import lombok.Getter;

@Getter
public class ItemCreateRequest {

    private String itemName;
    private int price;
    private int quantity;
    private String introduction;
    private String category;
}

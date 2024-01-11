package com.sparta.spartagoods.cart.entity;

import lombok.Getter;

@Getter
public class CartItemResponse {
    private String itemName;
    private int price;
    private int quantity;
    private String introduction;
    private String category;
    int count;

    public CartItemResponse(CartItem cartItem) {
        this.itemName = cartItem.getItem().getItemName();
        this.price = cartItem.getItem().getPrice();
        this.quantity = cartItem.getItem().getQuantity();
        this.introduction = cartItem.getItem().getIntroduction();
        this.category = cartItem.getItem().getCategory();
        this.count = cartItem.getCount();
    }
}

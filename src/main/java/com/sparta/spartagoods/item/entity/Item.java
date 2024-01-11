package com.sparta.spartagoods.item.entity;

import com.sparta.spartagoods.item.dto.ItemCreateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor

@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int price;

    private int quantity;

    private String introduction;

    private String category;

    public Item(ItemCreateRequest request) {
        this.itemName = request.getItemName();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.introduction = request.getIntroduction();
        this.category = request.getCategory();
    }
}

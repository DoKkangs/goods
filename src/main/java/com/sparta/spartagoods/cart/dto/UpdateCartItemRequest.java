package com.sparta.spartagoods.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCartItemRequest {
    @NotNull(message = "count cannot be null")
    @Min(value = 1, message = "최소 수량은 1입니다.")
    private int count;
}

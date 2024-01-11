package com.sparta.spartagoods.cart.controller;

import com.sparta.spartagoods.cart.dto.AddCartItemRequest;
import com.sparta.spartagoods.cart.dto.UpdateCartItemRequest;
import com.sparta.spartagoods.cart.entity.CartItemResponse;
import com.sparta.spartagoods.cart.service.CartService;
import com.sparta.spartagoods.user.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @PostMapping("/user/cart/{itemId}")
    public ResponseEntity<CartItemResponse> addCartItem(@PathVariable Long itemId,
                                                        @RequestBody @Valid AddCartItemRequest request,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return cartService.addCart(userDetails, itemId, request.getCount());
    }

    @GetMapping("/user/cart")
    public ResponseEntity<List<CartItemResponse>> getCartItems(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return cartService.getCartItems(userDetails);
    }

    @PutMapping("/user/cart/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long cartItemId,
                                                 @Valid @RequestBody UpdateCartItemRequest request) {
        return cartService.updateCartItem(cartItemId, request);
    }

    @DeleteMapping("/user/cart/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartItemId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.deleteCartItem(cartItemId);
    }

}

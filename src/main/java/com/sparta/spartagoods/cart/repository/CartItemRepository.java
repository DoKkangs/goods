package com.sparta.spartagoods.cart.repository;

import com.sparta.spartagoods.cart.entity.CartItem;
import com.sparta.spartagoods.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    List<CartItem> findAllByCartId(Long cartId);
}

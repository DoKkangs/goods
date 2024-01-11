package com.sparta.spartagoods.cart.repository;

import com.sparta.spartagoods.cart.entity.Cart;
import com.sparta.spartagoods.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long id);
}

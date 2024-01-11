package com.sparta.spartagoods.cart.service;

import com.sparta.spartagoods.cart.dto.UpdateCartItemRequest;
import com.sparta.spartagoods.cart.entity.Cart;
import com.sparta.spartagoods.cart.entity.CartItem;
import com.sparta.spartagoods.cart.entity.CartItemResponse;
import com.sparta.spartagoods.cart.repository.CartItemRepository;
import com.sparta.spartagoods.cart.repository.CartRepository;
import com.sparta.spartagoods.item.entity.Item;
import com.sparta.spartagoods.item.repository.ItemRepository;
import com.sparta.spartagoods.user.entity.User;
import com.sparta.spartagoods.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public ResponseEntity<CartItemResponse> addCart(UserDetailsImpl userDetails, Long itemId, int count) {
        try {
            // 해당 회원정보를 가져오고 상품이 존재하는지 체크
            User user = userDetails.getUser();
            Item item = itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
            // 그 회원이 장바구니를 가져온다
            Cart cart = cartRepository.findByUserId(user.getId());
            // 장바구니가 비어있다면 장바구니 생성
            if(cart == null){
                cart = Cart.createCart(user);
                cartRepository.save(cart);
            }
            // 장바구니에 넣을 아이템이 이미 장바구니에 들어있는지 확인을 위해 장바구니 상품을 가져옴
            CartItem cartItem =  cartItemRepository.findByCartIdAndItemId(cart.getId(),item.getId());
            if(cartItem == null){
                cartItem = new CartItem(cart, item, count);
                cartItemRepository.save(cartItem);
            }else {
                cartItem.addCount(count);
                cartItemRepository.save(cartItem);
            }
            cart.addCount(1);
            // 카트에 들어있는 상품의 총 개수
            return ResponseEntity.status(HttpStatus.CREATED).body(new CartItemResponse(cartItem));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<CartItemResponse>> getCartItems(UserDetailsImpl userDetails) {
        try {
            Cart cart = cartRepository.findByUserId(userDetails.getUser().getId());
            List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.getId());
            return ResponseEntity.ok(cartItems.stream().map(CartItemResponse::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    public ResponseEntity<String> updateCartItem(Long cartItemId, UpdateCartItemRequest request) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                    () -> new IllegalArgumentException("해당 상품은 장바구니에 존재하지 않습니다.")
            );
            cartItem.update(request.getCount());
            return ResponseEntity.ok().body("장바구니 수정 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("장바구니 상품이 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 수정 중 오류 발생");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteCartItem(Long cartItemId) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                    () -> new IllegalArgumentException("해당 상품은 장바구니에 존재하지 않습니다.")
            );
            cartItemRepository.delete(cartItem);
            return ResponseEntity.ok().body("장바구니 삭제 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

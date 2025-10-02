package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.*;
import com.swp391.bookverse.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling cart-related APIs
 * Supports functions: add books, view cart, update quantities, remove books
 */
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * UC-19.1: Add book to cart
     * @param addToCartDTO DTO containing book information and quantity to add
     * @param authentication Authentication information to get customer ID
     * @return ResponseEntity containing added cart item information
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @Valid @RequestBody AddToCartDTO addToCartDTO,
            Authentication authentication) {
        try {
            /** Get customer ID from authentication token */
            Long customerId = Long.parseLong(authentication.getName());
            /** Call service to execute add book to cart logic */
            CartItemDTO cartItem = cartService.addToCart(customerId, addToCartDTO);
            return ResponseEntity.ok(cartItem);
        } catch (RuntimeException e) {
            /** Return error if exception occurs */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * UC-19.2: View entire cart
     * @param authentication Authentication information to get customer ID
     * @return ResponseEntity containing entire cart information and total amount
     */
    @GetMapping
    public ResponseEntity<?> getCart(Authentication authentication) {
        try {
            /** Get customer ID from authentication token */
            Long customerId = Long.parseLong(authentication.getName());
            /** Call service to get cart information */
            CartDTO cart = cartService.getCart(customerId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            /** Return error if exception occurs */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * UC-19.3: Update book quantity in cart
     * @param cartItemId ID of cart item to update
     * @param updateQuantityDTO DTO containing new quantity
     * @param authentication Authentication information to get customer ID
     * @return ResponseEntity containing updated cart item information
     */
    @PutMapping("/item/{cartItemId}/quantity")
    public ResponseEntity<?> updateQuantity(
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateQuantityDTO updateQuantityDTO,
            Authentication authentication) {
        try {
            /** Get customer ID from authentication token */
            Long customerId = Long.parseLong(authentication.getName());
            /** Call service to update quantity */
            CartItemDTO cartItem = cartService.updateQuantity(customerId, cartItemId, updateQuantityDTO);
            return ResponseEntity.ok(cartItem);
        } catch (RuntimeException e) {
            /** Return error if exception occurs */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * UC-19.4: Remove book from cart
     * @param cartItemId ID of cart item to remove
     * @param authentication Authentication information to get customer ID
     * @return ResponseEntity with success message or error
     */
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<?> removeFromCart(
            @PathVariable Long cartItemId,
            Authentication authentication) {
        try {
            /** Get customer ID from authentication token */
            Long customerId = Long.parseLong(authentication.getName());
            /** Call service to remove item from cart */
            cartService.removeFromCart(customerId, cartItemId);
            return ResponseEntity.ok("Item removed from cart successfully");
        } catch (RuntimeException e) {
            /** Return error if exception occurs */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get cart item count (used for badge display on UI)
     * @param authentication Authentication information to get customer ID
     * @return ResponseEntity containing number of items in cart
     */
    @GetMapping("/count")
    public ResponseEntity<?> getCartItemCount(Authentication authentication) {
        try {
            /** Get customer ID from authentication token */
            Long customerId = Long.parseLong(authentication.getName());
            /** Call service to count items in cart */
            Integer count = cartService.getCartItemCount(customerId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            /** Return error if exception occurs */
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.*;
import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.entity.CartItem;
import com.swp391.bookverse.entity.Customer;
import com.swp391.bookverse.repository.BookRepository;
import com.swp391.bookverse.repository.CartItemRepository;
import com.swp391.bookverse.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for handling business logic of cart functionality
 * Provides methods: add, view, update, delete cart items
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    /**
     * UC-19.1: Add book to cart
     * Business Logic:
     * 1. Check if customer and book exist
     * 2. Check if stock is sufficient
     * 3. If already in cart -> merge quantities
     * 4. If not in cart -> create new CartItem
     * 5. Save to database and return DTO
     */
    public CartItemDTO addToCart(Long customerId, AddToCartDTO addToCartDTO) {
        /** Validate customer exists */
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        /** Validate book exists */
        Book book = bookRepository.findById(addToCartDTO.getBookId())
            .orElseThrow(() -> new RuntimeException("Book not found"));

        /** Check stock availability */
        if (book.getStock() < addToCartDTO.getQuantity()) {
            throw new RuntimeException("Insufficient stock available");
        }

        /** Check if item already exists in cart */
        Optional<CartItem> existingCartItem = cartItemRepository
            .findByCustomerIdAndBookId(customerId, addToCartDTO.getBookId());

        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            /** Merge with existing item */
            cartItem = existingCartItem.get();
            int newQuantity = cartItem.getQuantity() + addToCartDTO.getQuantity();

            /** Check stock again after merging */
            if (book.getStock() < newQuantity) {
                throw new RuntimeException("Insufficient stock available");
            }

            cartItem.setQuantity(newQuantity);
        } else {
            /** Create new cart item */
            cartItem = new CartItem();
            cartItem.setCustomer(customer);
            cartItem.setBook(book);
            cartItem.setQuantity(addToCartDTO.getQuantity());
        }

        /** Save cart item and convert to DTO */
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return convertToCartItemDTO(savedCartItem);
    }

    /**
     * UC-19.2: View cart
     * Business Logic:
     * 1. Get all items in customer's cart
     * 2. Convert to DTO with detailed information
     * 3. Calculate total quantity and total amount
     * 4. Return complete CartDTO
     */
    public CartDTO getCart(Long customerId) {
        /** Get all cart items for customer */
        List<CartItem> cartItems = cartItemRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);

        /** Convert to DTOs */
        List<CartItemDTO> cartItemDTOs = cartItems.stream()
            .map(this::convertToCartItemDTO)
            .collect(Collectors.toList());

        /** Build cart summary */
        CartDTO cartDTO = new CartDTO();
        cartDTO.setItems(cartItemDTOs);
        /** Calculate total items */
        cartDTO.setTotalItems(cartItemDTOs.stream().mapToInt(CartItemDTO::getQuantity).sum());
        /** Calculate total amount */
        cartDTO.setTotalAmount(cartItemDTOs.stream()
            .map(CartItemDTO::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add));

        return cartDTO;
    }

    /**
     * UC-19.3: Update book quantity in cart
     * Business Logic:
     * 1. Find CartItem by ID and customer
     * 2. Check if stock is sufficient for new quantity
     * 3. Update quantity
     * 4. Save and return DTO
     */
    public CartItemDTO updateQuantity(Long customerId, Long cartItemId, UpdateQuantityDTO updateQuantityDTO) {
        /** Find cart item belonging to customer */
        CartItem cartItem = cartItemRepository.findByIdAndCustomerId(cartItemId, customerId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        /** Check stock for new quantity */
        if (cartItem.getBook().getStock() < updateQuantityDTO.getQuantity()) {
            throw new RuntimeException("Insufficient stock available");
        }

        /** Update quantity */
        cartItem.setQuantity(updateQuantityDTO.getQuantity());
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return convertToCartItemDTO(savedCartItem);
    }

    /**
     * UC-19.4: Remove book from cart
     * Business Logic:
     * 1. Find CartItem by ID and customer
     * 2. Delete from database
     */
    public void removeFromCart(Long customerId, Long cartItemId) {
        /** Find and validate cart item ownership */
        CartItem cartItem = cartItemRepository.findByIdAndCustomerId(cartItemId, customerId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        /** Delete from database */
        cartItemRepository.delete(cartItem);
    }

    /**
     * Get cart item count - used for UI badge display
     * @param customerId Customer ID
     * @return Number of items in cart
     */
    public Integer getCartItemCount(Long customerId) {
        return cartItemRepository.countByCustomerId(customerId);
    }

    /**
     * Helper method: Convert Entity to DTO
     * Converts CartItem entity to CartItemDTO with complete information
     * Includes: book info, quantity, subtotal, available stock
     * @param cartItem CartItem entity to convert
     * @return CartItemDTO with complete information
     */
    private CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        /** Set basic cart item info */
        dto.setId(cartItem.getId());
        dto.setBookId(cartItem.getBook().getId());
        /** Set book details */
        dto.setBookTitle(cartItem.getBook().getTitle());
        dto.setBookAuthor(cartItem.getBook().getAuthor());
        dto.setBookPrice(cartItem.getBook().getPrice());
        dto.setBookImageUrl(cartItem.getBook().getImageUrl());
        /** Set quantity and calculations */
        dto.setQuantity(cartItem.getQuantity());
        /** Calculate subtotal = price * quantity */
        dto.setSubtotal(cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        /** Set available stock for UI validation */
        dto.setAvailableStock(cartItem.getBook().getStock());
        return dto;
    }
}

package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.request.PromotionRequest;
import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.entity.Promotion;
import com.swp391.bookverse.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing promotions.
 * Provides APIs for creating, retrieving, updating, and deleting promotions.
 *
 * @Author: NhaNT9324W
 */
@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    // Inject PromotionService to handle business logic
    private final PromotionService promotionService;

    /**
     * Create a new promotion.
     *
     * @param request PromotionRequest containing promotion information
     * @return APIResponse with created Promotion object
     */
    @PostMapping
    public APIResponse<Promotion> createPromotion(@Valid @RequestBody PromotionRequest request) {
        return APIResponse.<Promotion>builder()
                .result(promotionService.createPromotion(request))
                .build();
    }

    /**
     * Get all promotions from the system.
     *
     * @return APIResponse containing list of promotions
     */
    @GetMapping
    public APIResponse<List<Promotion>> getAllPromotions() {
        return APIResponse.<List<Promotion>>builder()
                .result(promotionService.getAllPromotions())
                .build();
    }

    /**
     * Get a promotion by its ID.
     *
     * @param id promotion ID
     * @return APIResponse containing promotion data if found
     */
    @GetMapping("/{id}")
    public APIResponse<Promotion> getPromotionById(@PathVariable Long id) {
        return APIResponse.<Promotion>builder()
                .result(promotionService.getPromotionById(id))
                .build();
    }

    /**
     * Update promotion information by ID.
     *
     * @param id promotion ID to update
     * @param request new promotion data
     * @return APIResponse containing updated promotion
     */
    @PutMapping("/{id}")
    public APIResponse<Promotion> updatePromotion(@PathVariable Long id, @Valid @RequestBody PromotionRequest request) {
        return APIResponse.<Promotion>builder()
                .result(promotionService.updatePromotion(id, request))
                .build();
    }

    /**
     * Delete promotion by ID.
     *
     * @param id promotion ID to delete
     * @return APIResponse with success message
     */
    @DeleteMapping("/{id}")
    public APIResponse<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return APIResponse.<Void>builder()
                .message("Promotion deleted successfully.")
                .build();
    }
}

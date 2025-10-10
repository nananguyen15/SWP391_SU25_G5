package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.request.PromotionRequest;
import com.swp391.bookverse.entity.Promotion;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for handling business logic related to promotions.
 * It provides CRUD operations and validation for promotion entities.
 *
 * @Author: NhaNT9324W
 */
@Service
@RequiredArgsConstructor
public class PromotionService {

    // Repository to interact with the Promotion database table
    private final PromotionRepository promotionRepository;

    /**
     * Create a new promotion based on request data.
     *
     * @param request PromotionRequest containing promotion details
     * @return newly created Promotion entity
     */
    public Promotion createPromotion(PromotionRequest request) {
        Promotion newPromotion = Promotion.builder()
                .content(request.getContent())
                .percentage(request.getPercentage())
                .type(request.getType())
                .promotionDay(request.getPromotionDay())
                .build();

        return promotionRepository.save(newPromotion);
    }

    /**
     * Get all promotions from the system.
     *
     * @return list of all Promotion entities
     */
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    /**
     * Get a promotion by its ID.
     *
     * @param id ID of the promotion to retrieve
     * @return found Promotion entity
     * @throws AppException if promotion not found
     */
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
    }

    /**
     * Update an existing promotion by ID.
     *
     * @param id ID of the promotion to update
     * @param request PromotionRequest containing new promotion data
     * @return updated Promotion entity
     */
    public Promotion updatePromotion(Long id, PromotionRequest request) {
        Promotion existingPromotion = getPromotionById(id);

        existingPromotion.setContent(request.getContent());
        existingPromotion.setPercentage(request.getPercentage());
        existingPromotion.setType(request.getType());
        existingPromotion.setPromotionDay(request.getPromotionDay());

        return promotionRepository.save(existingPromotion);
    }

    /**
     * Delete a promotion by ID.
     *
     * @param id ID of the promotion to delete
     * @throws AppException if promotion not found
     */
    public void deletePromotion(Long id) {
        Promotion promotionToDelete = getPromotionById(id);
        promotionRepository.delete(promotionToDelete);
    }
}

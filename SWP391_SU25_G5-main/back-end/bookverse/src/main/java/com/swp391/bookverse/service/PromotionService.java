package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.Promotion;
import com.swp391.bookverse.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: NhaNT9324W
 * Service xu ly logic cho Promotion
 * - UC-74: Create
 * - UC-75: Update
 * - UC-76: Delete
 * - UC-77: View
 */
@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    // Tao khuyen mai moi
    public Promotion createPromotion(Promotion promotion) {
        validatePromotionDates(promotion);
        return promotionRepository.save(promotion);
    }

    // Cap nhat khuyen mai
    public Promotion updatePromotion(Long id, Promotion promotionDetails) {
        Promotion existing = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));

        existing.setTitle(promotionDetails.getTitle());
        existing.setDescription(promotionDetails.getDescription());
        existing.setStartDate(promotionDetails.getStartDate());
        existing.setEndDate(promotionDetails.getEndDate());
        existing.setActive(promotionDetails.getActive());
        existing.setType(promotionDetails.getType());

        validatePromotionDates(existing);
        return promotionRepository.save(existing);
    }

    // Xoa khuyen mai
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    // Lay tat ca khuyen mai
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    // Lay chi tiet khuyen mai
    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    // Validate ngay bat dau va ket thuc
    private void validatePromotionDates(Promotion promotion) {
        if (promotion.getEndDate().isBefore(promotion.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }
}

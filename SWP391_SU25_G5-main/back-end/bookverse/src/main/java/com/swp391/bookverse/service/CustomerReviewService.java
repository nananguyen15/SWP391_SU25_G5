package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.repository.CustomerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: NhaNT9324W
 * Service xu ly logic cho review cua khach hang
 * - UC-66: Create review
 * - UC-67: Read reviews
 * - UC-68: Update review
 * - UC-69: Delete review
 */
@Service
@RequiredArgsConstructor
public class CustomerReviewService {

    private final CustomerReviewRepository reviewRepository;

    // Tao review moi
    public CustomerReview createReview(CustomerReview review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // Lay tat ca review cua sach
    public List<CustomerReview> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    // Lay tat ca review cua khach hang
    public List<CustomerReview> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    // Cap nhat review
    public CustomerReview updateReview(Long reviewId, Long customerId, Integer rating, String comment) {
        CustomerReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getCustomerId().equals(customerId)) {
            throw new RuntimeException("You do not have permission to update this review");
        }

        review.setRating(rating);
        review.setComment(comment);
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    // Xoa review
    public void deleteReview(Long reviewId, Long customerId) {
        CustomerReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getCustomerId().equals(customerId)) {
            throw new RuntimeException("You do not have permission to delete this review");
        }

        reviewRepository.delete(review);
    }
}

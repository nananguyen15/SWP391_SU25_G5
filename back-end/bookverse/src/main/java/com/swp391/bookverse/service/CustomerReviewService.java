package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.repository.CustomerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: Service layer for managing customer reviews.
 *                Handles business logic for creating, updating, retrieving, and deleting reviews.
 * @Package: com.swp391.bookverse.service
 */
@Service
@RequiredArgsConstructor
public class CustomerReviewService {

    private final CustomerReviewRepository customerReviewRepository;

    /**
     * Create a new customer review.
     * Validates that the user has not reviewed the same book before
     * and that the rating value is within the valid range (1–5).
     *
     * @param review CustomerReview object containing review data.
     * @return Saved CustomerReview object.
     * @throws RuntimeException if validation fails.
     */
    public CustomerReview createReview(CustomerReview review) {
        // Check if the user has already reviewed this book
        if (customerReviewRepository.existsByUserIdAndBookId(review.getUserId(), review.getBookId())) {
            throw new RuntimeException("You have already reviewed this book");
        }

        // Validate rating value
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5 stars");
        }

        return customerReviewRepository.save(review);
    }

    /**
     * Retrieve all reviews created by a specific customer.
     *
     * @param customerId ID of the customer.
     * @return List of CustomerReview objects.
     */
    public List<CustomerReview> getReviewByCustomer(String customerId) {
        return customerReviewRepository.findByUserId(customerId);
    }

    /**
     * Update an existing review owned by a specific customer.
     * Only rating and comment fields can be modified.
     *
     * @param reviewId ID of the review to update.
     * @param customerId ID of the customer performing the update.
     * @param updatedReview Updated review data (rating/comment).
     * @return Updated CustomerReview object.
     * @throws RuntimeException if the review is not found or validation fails.
     */
    public CustomerReview editReview(Long reviewId, String customerId, CustomerReview updatedReview) {
        Optional<CustomerReview> existingReviewOpt = customerReviewRepository.findByIdAndUserId(reviewId, customerId);

        if (existingReviewOpt.isEmpty()) {
            throw new RuntimeException("Review not found or you are not authorized to edit it");
        }

        CustomerReview existingReview = existingReviewOpt.get();

        // Update rating if provided and valid
        if (updatedReview.getRating() != null) {
            if (updatedReview.getRating() < 1 || updatedReview.getRating() > 5) {
                throw new RuntimeException("Rating must be between 1 and 5 stars");
            }
            existingReview.setRating(updatedReview.getRating());
        }

        // Update comment if provided
        if (updatedReview.getComment() != null) {
            existingReview.setComment(updatedReview.getComment());
        }

        return customerReviewRepository.save(existingReview);
    }

    /**
     * Delete a review belonging to a specific customer.
     *
     * @param reviewId ID of the review to delete.
     * @param customerId ID of the customer performing the deletion.
     * @throws RuntimeException if the review is not found or unauthorized.
     */
    public void deleteReview(Long reviewId, String customerId) {
        Optional<CustomerReview> reviewOpt = customerReviewRepository.findByIdAndUserId(reviewId, customerId);

        if (reviewOpt.isEmpty()) {
            throw new RuntimeException("Review not found or you are not authorized to delete it");
        }

        customerReviewRepository.deleteById(reviewId);
    }

    /**
     * Retrieve a single review by its ID.
     *
     * @param reviewId ID of the review.
     * @return Optional containing the CustomerReview if found.
     */
    public Optional<CustomerReview> getReviewById(Long reviewId) {
        return customerReviewRepository.findById(reviewId);
    }

    /**
     * Retrieve all reviews for a specific book.
     *
     * @param bookId ID of the book.
     * @return List of CustomerReview objects.
     */
    public List<CustomerReview> getReviewsByBookId(Long bookId) {
        return customerReviewRepository.findByBookId(bookId);
    }
}

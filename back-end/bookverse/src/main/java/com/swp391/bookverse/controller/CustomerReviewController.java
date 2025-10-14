package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.request.ReviewRequest;
import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.service.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: REST Controller that manages Customer Review operations.
 *                - Allows customer to create, view, update, and delete their reviews.
 *                - Accessible mainly by Customer role.
 * @BaseURL: /customer-reviews
 */
@RestController
@RequestMapping("/customer-reviews")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService customerReviewService;

    /**
     * POST /customer-reviews
     * Description: Create a new customer review.
     * Role: CUSTOMER
     *
     * @param reviewRequest The DTO containing userId, bookId, rating, and comment.
     * @return ResponseEntity containing the created review or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            // Convert DTO to Entity
            CustomerReview review = new CustomerReview();
            review.setUserId(reviewRequest.getUserId());
            review.setBookId(reviewRequest.getBookId());
            review.setRating(reviewRequest.getRating());
            review.setComment(reviewRequest.getComment());

            CustomerReview createdReview = customerReviewService.createReview(review);
            return ResponseEntity.ok(createdReview);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * GET /customer-reviews
     * Description: Retrieve all reviews created by a specific customer.
     * Role: CUSTOMER
     *
     * @param customerId The ID of the customer.
     * @return ResponseEntity containing the list of reviews or an error message.
     */
    @GetMapping
    public ResponseEntity<?> getCustomerReviews(@RequestParam String customerId) {
        try {
            List<CustomerReview> reviews = customerReviewService.getReviewByCustomer(customerId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * GET /customer-reviews/book/{bookId}
     * Description: Retrieve all reviews of a specific book.
     * Role: PUBLIC (Guest/Customer/Staff/Admin)
     *
     * @param bookId The ID of the book.
     * @return ResponseEntity containing the list of reviews or an error message.
     */
    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getReviewsByBook(@PathVariable Long bookId) {
        try {
            List<CustomerReview> reviews = customerReviewService.getReviewsByBookId(bookId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * PUT customer-reviews/{id}
     * Description: Update an existing customer review.
     * Role: CUSTOMER
     *
     * @param id The ID of the review.
     * @param customerId The ID of the customer performing the update.
     * @param updatedReviewRequest DTO containing updated rating and comment.
     * @return ResponseEntity containing the updated review or an error message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(
            @PathVariable Long id,
            @RequestParam String customerId,
            @RequestBody ReviewRequest updatedReviewRequest) {
        try {
            // Convert DTO to Entity
            CustomerReview updatedReview = new CustomerReview();
            updatedReview.setRating(updatedReviewRequest.getRating());
            updatedReview.setComment(updatedReviewRequest.getComment());

            CustomerReview review = customerReviewService.editReview(id, customerId, updatedReview);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * DELETE customer-reviews/{id}
     * Description: Delete a customer review.
     * Role: CUSTOMER
     *
     * @param id The ID of the review.
     * @param customerId The ID of the customer performing deletion.
     * @return ResponseEntity confirming the deletion or an error message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long id,
            @RequestParam String customerId) {
        try {
            customerReviewService.deleteReview(id, customerId);
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * GET /api/customer-reviews/{id}
     * Description: Retrieve details of a specific review by its ID.
     * Role: PUBLIC (Guest/Customer/Staff/Admin)
     *
     * @param id The ID of the review.
     * @return ResponseEntity containing the review details or an error message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try {
            return customerReviewService.getReviewById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

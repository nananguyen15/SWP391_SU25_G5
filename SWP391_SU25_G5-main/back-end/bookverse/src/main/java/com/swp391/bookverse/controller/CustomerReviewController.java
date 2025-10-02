package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.service.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: NhaNT9324W
 */
@RestController
@RequestMapping("/api/customer-reviews")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService reviewService;

    // UC-66: Create customer review
    @PostMapping
    public ResponseEntity<CustomerReview> createReview(@RequestBody CustomerReview review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    // UC-67: View customer reviews by book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<CustomerReview>> getReviewsByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewService.getReviewsByBook(bookId));
    }

    // UC-67: View customer reviews by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerReview>> getReviewsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }

    // UC-68: Edit customer review
    @PutMapping("/{reviewId}")
    public ResponseEntity<CustomerReview> updateReview(
            @PathVariable Long reviewId,
            @RequestBody CustomerReview updatedReview) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, updatedReview));
    }

    // UC-69: Delete customer review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}

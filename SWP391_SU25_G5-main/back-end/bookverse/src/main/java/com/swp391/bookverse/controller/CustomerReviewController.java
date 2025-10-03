package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.request.CustomerReviewRequest;
import com.swp391.bookverse.dto.response.CustomerReviewResponse;
import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.service.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: NhaNT9324W
 * REST Controller cho review cua khach hang
 * Base mapping: /api/customer-reviews
 */
@RestController
@RequestMapping("/api/customer-reviews")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService reviewService;

    // UC-66: Tao review moi
    @PostMapping
    public ResponseEntity<CustomerReviewResponse> createReview(@RequestBody CustomerReviewRequest request) {
        CustomerReview review = CustomerReview.builder()
                .customerId(request.getCustomerId())
                .bookId(request.getBookId())
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        CustomerReview created = reviewService.createReview(review);

        return ResponseEntity.ok(CustomerReviewResponse.fromEntity(created));
    }

    // UC-67: Lay review cua sach
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<CustomerReviewResponse>> getReviewsByBook(@PathVariable Long bookId) {
        List<CustomerReview> reviews = reviewService.getReviewsByBook(bookId);

        List<CustomerReviewResponse> responseList = reviews.stream()
                .map(CustomerReviewResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // UC-68: Cap nhat review
    @PutMapping("/{reviewId}")
    public ResponseEntity<CustomerReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestParam Long customerId,
            @RequestBody CustomerReviewRequest request
    ) {
        CustomerReview updated = reviewService.updateReview(reviewId, customerId, request.getRating(), request.getComment());
        return ResponseEntity.ok(CustomerReviewResponse.fromEntity(updated));
    }

    // UC-69: Xoa review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long customerId
    ) {
        reviewService.deleteReview(reviewId, customerId);
        return ResponseEntity.noContent().build();
    }
}

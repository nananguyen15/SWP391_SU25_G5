package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Controller expose API review danh cho Admin/Staff
 * Base mapping: /api/reviews
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // UC-70/71/72: Lay tat ca hoac tim kiem/loc review
    @GetMapping
    public ResponseEntity<List<CustomerReview>> getReviews(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) Long customerId
    ) {
        List<CustomerReview> reviews = reviewService.searchReviews(keyword, bookId, customerId);
        return ResponseEntity.ok(reviews);
    }

    // UC-73: Xoa review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}

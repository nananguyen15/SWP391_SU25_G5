package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling review management requests from administrators and staff.
 * It exposes endpoints that allow authorized users to view, search, filter, and delete
 * customer reviews across the entire platform.
 *
 * @Author: NhaNT9324W
 */
@RestController
@RequestMapping("/review") // k cần chữ API/ đâu vậy là đủ
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Handles the GET request to fetch, search, and filter all reviews.
     * This endpoint is intended for Admin and Staff roles and accepts optional
     * query parameters to refine the result set.
     *
     * @param keyword Optional. A keyword to search within the review comments.
     * @param bookId  Optional. The ID of the book to filter reviews by.
     * @param userId  Optional. The ID of the user (customer) to filter reviews by.
     * @return An {@link APIResponse} containing a list of matching {@link CustomerReview} entities.
     */
    @GetMapping
    public APIResponse<List<CustomerReview>> getAllReviews(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) String userId) {

        List<CustomerReview> reviews = reviewService.searchAndFilterReviews(keyword, bookId, userId);
        return APIResponse.<List<CustomerReview>>builder()
                .result(reviews)
                .build();
    }

    /**
     * Handles the DELETE request to remove a specific review by its ID.
     * This endpoint is intended for Admin and Staff roles to moderate content.
     *
     * @param id The unique identifier of the review to be deleted.
     * @return An {@link APIResponse} with a success message confirming the deletion.
     */
    @DeleteMapping("/{id}")
    public APIResponse<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return APIResponse.<Void>builder()
                .message("Review deleted successfully.")
                .build();
    }
}
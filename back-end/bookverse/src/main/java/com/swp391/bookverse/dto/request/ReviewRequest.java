package com.swp391.bookverse.dto.request;

import lombok.Data;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: Data Transfer Object (DTO) used for creating or updating customer reviews.
 *                This DTO is received from client requests and transferred to the service layer.
 * @Package: com.swp391.bookverse.dto.request
 */
@Data
public class ReviewRequest {

    /**
     * ID of the user who creates or updates the review.
     * Typically corresponds to the logged-in customer.
     */
    private String userId;

    /**
     * ID of the book that the review refers to.
     */
    private Long bookId;

    /**
     * Rating score given by the customer (range: 1–5).
     */
    private Integer rating;

    /**
     * Text content of the customer’s review or comment.
     */
    private String comment;
}

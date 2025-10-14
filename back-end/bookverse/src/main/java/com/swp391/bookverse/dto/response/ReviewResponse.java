package com.swp391.bookverse.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: Data Transfer Object (DTO) used for returning review details
 *                from the backend to the client after creation, update, or retrieval.
 * @Package: com.swp391.bookverse.dto.response
 */
@Data
public class ReviewResponse {

    /**
     * Unique identifier of the review.
     */
    private Long id;

    /**
     * ID of the user who created the review.
     */
    private String userId;

    /**
     * ID of the book that this review belongs to.
     */
    private Long bookId;

    /**
     * Rating value given by the customer (1–5 scale).
     */
    private Integer rating;

    /**
     * Comment content written by the customer.
     */
    private String comment;

    /**
     * Timestamp representing when the review was created.
     */
    private LocalDateTime createdAt;
}

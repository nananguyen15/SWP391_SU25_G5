package com.swp391.bookverse.dto.response;

import com.swp391.bookverse.entity.CustomerReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO response tra ve thong tin review
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerReviewResponse {
    private Long id;
    private Long customerId;
    private Long bookId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CustomerReviewResponse fromEntity(CustomerReview review) {
        return CustomerReviewResponse.builder()
                .id(review.getId())
                .customerId(review.getCustomerId())
                .bookId(review.getBookId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}

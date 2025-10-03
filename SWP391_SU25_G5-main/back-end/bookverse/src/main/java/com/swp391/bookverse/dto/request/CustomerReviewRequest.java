package com.swp391.bookverse.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO request cho review (create/update)
 */
@Getter
@Setter
public class CustomerReviewRequest {
    private Long customerId;
    private Long bookId;
    private Integer rating;
    private String comment;
}

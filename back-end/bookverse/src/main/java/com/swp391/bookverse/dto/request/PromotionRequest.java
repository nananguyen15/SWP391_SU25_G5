package com.swp391.bookverse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) for receiving promotion creation or update requests.
 * Used to validate and transfer data between client and server.
 *
 * @Author: NhaNT9324W
 */
@Data
public class PromotionRequest {

    // Description or content of the promotion
    @NotBlank(message = "Content cannot be blank")
    private String content;

    // Discount percentage (must be >= 0)
    @NotNull(message = "Percentage cannot be null")
    @PositiveOrZero(message = "Percentage must be zero or positive")
    private Integer percentage;

    // Type of promotion (e.g., discount, coupon, sale)
    @NotBlank(message = "Type cannot be blank")
    private String type;

    // The day when the promotion is applied
    @NotNull(message = "Promotion day cannot be null")
    private LocalDate promotionDay;
}

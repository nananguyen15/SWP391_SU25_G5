package com.swp391.bookverse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO response tra ve thong tin thong bao cho client
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerNotificationResponse {
    private Long id;
    private String content;
    private String type;
    private Boolean isRead;
    private LocalDateTime createdAt;
}

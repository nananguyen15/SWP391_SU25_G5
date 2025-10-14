package com.swp391.bookverse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO response used to return notification details to the client.
 * Contains information such as notification content, type, status, and creation time.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerNotificationResponse {

    /**
     * Unique identifier of the notification.
     */
    private Long id;

    /**
     * The main message or content of the notification.
     */
    private String content;

    /**
     * The type of notification (e.g., system, order, message, etc.).
     */
    private String type;

    /**
     * Indicates whether the notification has been read (true) or not (false).
     */
    private Boolean isRead;

    /**
     * The date and time when the notification was created.
     */
    private LocalDateTime createdAt;
}

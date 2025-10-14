package com.swp391.bookverse.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO request used to update the read/unread status of a customer notification.
 * This object is sent from the client to indicate whether a notification has been read.
 */
@Getter
@Setter
public class NotificationStatusRequest {

    /**
     * Indicates whether the notification is read (true) or unread (false).
     */
    private Boolean isRead;
}

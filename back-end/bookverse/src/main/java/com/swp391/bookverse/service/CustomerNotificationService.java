package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.repository.CustomerNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Service class responsible for handling customer notification logic.
 *
 * <p>Use Cases:</p>
 * <ul>
 *   <li><b>UC-64:</b> View customer notifications</li>
 *   <li><b>UC-65:</b> Update customer notification status</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class CustomerNotificationService {

    /** Repository for accessing customer notification data from the database. */
    private final CustomerNotificationRepository notificationRepository;

    /**
     * Retrieves all notifications belonging to a specific user.
     *
     * @param userId the ID of the user whose notifications will be retrieved
     * @return a list of {@link CustomerNotification} objects for the given user
     */
    public List<CustomerNotification> getCustomerNotifications(String userId) {
        // Query the database for all notifications associated with the provided userId
        return notificationRepository.findByUserId(userId);
    }

    /**
     * Updates the read/unread status of a specific notification for a user.
     * Ensures that the notification belongs to the given user before updating.
     *
     * @param notificationId the ID of the notification to update
     * @param userId the ID of the user who owns the notification
     * @param isRead the new read status (true = read, false = unread)
     * @return the updated {@link CustomerNotification} entity
     */
    public CustomerNotification updateNotificationStatus(Long notificationId, String userId, Boolean isRead) {
        // Find the notification by ID and ensure it belongs to the specified user
        CustomerNotification notification = notificationRepository
                .findByIdAndUser_Id(notificationId, userId)
                .orElseThrow(() -> new RuntimeException("Notification not found or does not belong to this user"));

        // Update the read/unread status
        notification.setIsRead(isRead);

        // Save the updated notification entity to the database
        return notificationRepository.save(notification);
    }
}

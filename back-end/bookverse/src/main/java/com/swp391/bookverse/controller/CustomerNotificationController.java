package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.request.NotificationStatusRequest;
import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.service.CustomerNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling customer notifications.
 * Provides endpoints for retrieving and updating notification status.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class CustomerNotificationController {

    // Inject the CustomerNotificationService to handle business logic
    private final CustomerNotificationService notificationService;

    /**
     * UC-64: Get all notifications for a specific customer by userId.
     * Endpoint: GET /api/notification/{userId}
     *
     * @param userId the ID of the customer whose notifications will be fetched
     * @return a list of CustomerNotification objects
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<CustomerNotification>> getNotifications(@PathVariable String userId) {
        // Retrieve the list of notifications for the specified user
        List<CustomerNotification> notifications = notificationService.getCustomerNotifications(userId);
        // Return the list wrapped in an HTTP 200 OK response
        return ResponseEntity.ok(notifications);
    }

    /**
     * UC-65: Update the read/unread status of a notification.
     * Endpoint: PUT /api/notification/{userId}/{id}
     *
     * @param userId  the ID of the customer
     * @param id      the ID of the notification to be updated
     * @param request the request body containing the new read status (isRead)
     * @return the updated CustomerNotification object
     */
    @PutMapping("/{userId}/{id}")
    public ResponseEntity<CustomerNotification> updateNotificationStatus(
            @PathVariable String userId,
            @PathVariable Long id,
            @RequestBody NotificationStatusRequest request
    ) {
        // Call the service to update notification status based on given id and userId
        CustomerNotification updated =
                notificationService.updateNotificationStatus(id, userId, request.getIsRead());
        // Return the updated notification wrapped in an HTTP 200 OK response
        return ResponseEntity.ok(updated);
    }

}

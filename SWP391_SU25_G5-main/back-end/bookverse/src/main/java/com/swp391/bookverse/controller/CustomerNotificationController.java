package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.service.CustomerNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * API REST cho UC-64, UC-65
 * - UC-64: View customer notifications
 * - UC-65: Update customer status notification
 */
@RestController
@RequestMapping("/api/notifications")
public class CustomerNotificationController {

    private final CustomerNotificationService notificationService;

    public CustomerNotificationController(CustomerNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * UC-64: Lay danh sach thong bao cua khach hang
     * GET /api/notifications?customerId={customerId}
     */
    @GetMapping
    public ResponseEntity<List<CustomerNotification>> getNotifications(@RequestParam Long customerId) {
        List<CustomerNotification> notifications = notificationService.getNotificationsByCustomerId(customerId);
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notifications);
    }

    /**
     * UC-65: Cap nhat trang thai 1 notification thanh READ
     * PUT /api/notifications/{id}/read
     */
    @PutMapping("/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        boolean success = notificationService.markNotificationAsRead(id);
        if (success) {
            return ResponseEntity.ok("Notification marked as READ.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * UC-65: Mark all notifications cua khach hang thanh READ
     * PUT /api/notifications/read-all?customerId={customerId}
     */
    @PutMapping("/read-all")
    public ResponseEntity<String> markAllAsRead(@RequestParam Long customerId) {
        int updatedCount = notificationService.markAllAsRead(customerId);
        return ResponseEntity.ok(updatedCount + " notifications marked as READ.");
    }
}

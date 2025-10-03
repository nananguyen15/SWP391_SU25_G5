package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.request.NotificationStatusRequest;
import com.swp391.bookverse.dto.response.CustomerNotificationResponse;
import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.service.CustomerNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: NhaNT9324W
 * REST Controller expose API cho thong bao cua khach hang
 * Base mapping: /api/customer-notifications
 */
@RestController
@RequestMapping("/api/customer-notifications")
@RequiredArgsConstructor
public class CustomerNotificationController {

    private final CustomerNotificationService notificationService;

    // UC-64: Lay tat ca thong bao cua khach hang
    @GetMapping
    public ResponseEntity<List<CustomerNotificationResponse>> getNotifications(@RequestParam Long userId) {
        List<CustomerNotification> notifications = notificationService.getCustomerNotifications(userId);

        List<CustomerNotificationResponse> responseList = notifications.stream()
                .map(n -> CustomerNotificationResponse.builder()
                        .id(n.getId())
                        .content(n.getContent())
                        .type(n.getType())
                        .isRead(n.getIsRead())
                        .createdAt(n.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // UC-65: Cap nhat trang thai thong bao
    @PutMapping("/{id}")
    public ResponseEntity<CustomerNotificationResponse> updateNotificationStatus(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody NotificationStatusRequest request
    ) {
        CustomerNotification updated = notificationService.updateNotificationStatus(id, userId, request.getIsRead());

        CustomerNotificationResponse response = CustomerNotificationResponse.builder()
                .id(updated.getId())
                .content(updated.getContent())
                .type(updated.getType())
                .isRead(updated.getIsRead())
                .createdAt(updated.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }
}

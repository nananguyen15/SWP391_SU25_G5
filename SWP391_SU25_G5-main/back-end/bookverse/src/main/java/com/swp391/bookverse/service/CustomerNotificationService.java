package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.repository.CustomerNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Service xu ly logic thong bao cua khach hang
 * - UC-64: View customer notifications
 * - UC-65: Update customer status notification
 */
@Service
@RequiredArgsConstructor
public class CustomerNotificationService {

    private final CustomerNotificationRepository notificationRepository;

    // Lay danh sach thong bao theo userId
    public List<CustomerNotification> getCustomerNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Cap nhat trang thai doc cua thong bao
    public CustomerNotification updateNotificationStatus(Long notificationId, Long userId, Boolean isRead) {
        CustomerNotification notification = notificationRepository
                .findByIdAndUserId(notificationId, userId)
                .orElseThrow(() -> new RuntimeException("Notification not found or not belongs to user"));

        notification.setIsRead(isRead);
        return notificationRepository.save(notification);
    }
}

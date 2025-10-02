package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerNotification;
import com.swp391.bookverse.repository.CustomerNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: NhaNT9324W
 * Service xu ly logic thong bao cua khach hang
 * - UC-64: View customer notifications
 * - UC-65: Update customer status notification
 */
@Service
public class CustomerNotificationService {

    private final CustomerNotificationRepository notificationRepository;

    // Constructor injection
    public CustomerNotificationService(CustomerNotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * UC-64: Lay danh sach thong bao cua khach hang theo customerId
     */
    public List<CustomerNotification> getNotificationsByCustomerId(Long customerId) {
        return notificationRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    /**
     * UC-65: Cap nhat trang thai 1 thong bao thanh READ
     */
    public boolean markNotificationAsRead(Long notificationId) {
        Optional<CustomerNotification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            CustomerNotification notification = optionalNotification.get();
            if (notification.getStatus() != CustomerNotification.NotificationStatus.READ) {
                notification.markAsRead();
                notificationRepository.save(notification);
            }
            return true;
        }
        return false; // Notification khong ton tai
    }

    /**
     * UC-65: Mark all notifications cua khach hang thanh READ
     */
    public int markAllAsRead(Long customerId) {
        List<CustomerNotification> notifications =
                notificationRepository.findByCustomerIdAndStatusOrderByCreatedAtDesc(
                        customerId,
                        CustomerNotification.NotificationStatus.UNREAD
                );
        notifications.forEach(CustomerNotification::markAsRead);
        notificationRepository.saveAll(notifications);
        return notifications.size(); // Tra ve so thong bao da cap nhat
    }
}

package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Repository thao tac DB thong bao khach hang
 * - UC-64: View customer notifications
 * - UC-65: Update customer status notification
 */
@Repository
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Long> {

    /**
     * Lay danh sach thong bao theo customerId
     */
    List<CustomerNotification> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    /**
     * Lay danh sach thong bao theo customerId va status
     */
    List<CustomerNotification> findByCustomerIdAndStatusOrderByCreatedAtDesc(
            Long customerId,
            CustomerNotification.NotificationStatus status
    );
}

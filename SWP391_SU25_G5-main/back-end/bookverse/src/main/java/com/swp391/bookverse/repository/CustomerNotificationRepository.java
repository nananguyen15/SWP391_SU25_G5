package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: NhaNT9324W
 * Repository thao tac voi bang notifications
 */
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Long> {

    // Lay danh sach thong bao cua 1 khach hang
    List<CustomerNotification> findByUserId(Long userId);

    // Tim thong bao theo id va userId (bao dam khach chi thay thong bao cua minh)
    Optional<CustomerNotification> findByIdAndUserId(Long id, Long userId);
}

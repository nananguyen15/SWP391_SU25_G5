package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * Entity luu thong tin thong bao cua khach hang (UC-64, UC-65)
 * - UC-64: View customer notifications
 * - UC-65: Update customer status notification
 */
@Entity
@Table(name = "customer_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khoa chinh

    @Column(name = "customer_id", nullable = false)
    private Long customerId; // Id cua khach hang nhan thong bao

    @Column(nullable = false, length = 200)
    private String title; // Tieu de thong bao

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Noi dung thong bao

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationStatus status; // Trang thai thong bao (UNREAD, READ)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Thoi gian tao

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thoi gian cap nhat

    /**
     * Enum luu trang thai thong bao
     */
    public enum NotificationStatus {
        UNREAD,
        READ
    }

    // Them phuong thuc tien ich neu can
    public void markAsRead() {
        this.status = NotificationStatus.READ;
        this.updatedAt = LocalDateTime.now();
    }
}

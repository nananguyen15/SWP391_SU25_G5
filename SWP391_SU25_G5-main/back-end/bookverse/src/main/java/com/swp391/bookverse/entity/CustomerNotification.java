package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * Entity map voi bang notifications (luu thong bao cua khach hang)
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tham chieu den user nhan thong bao
    @Column(nullable = false)
    private Long userId;

    // Noi dung thong bao
    @Column(nullable = false, length = 500)
    private String content;

    // Loai thong bao: INFO, PROMOTION...
    @Column(nullable = false, length = 50)
    private String type;

    // Trang thai thong bao da doc hay chua
    @Column(nullable = false)
    private Boolean isRead = false;

    // Thoi gian tao thong bao
    @Column(nullable = false)
    private LocalDateTime createdAt;
}

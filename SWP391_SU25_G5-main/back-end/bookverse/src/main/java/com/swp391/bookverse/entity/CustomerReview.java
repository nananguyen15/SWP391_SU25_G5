package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * Entity map voi bang reviews (luu review cua khach hang cho sach)
 */
@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tham chieu den khach hang
    @Column(nullable = false)
    private Long customerId;

    // Tham chieu den sach
    @Column(nullable = false)
    private Long bookId;

    // Diem danh gia 1-5
    @Column(nullable = false)
    private Integer rating;

    // Noi dung review
    @Column(length = 1000)
    private String comment;

    // Thoi gian tao
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Thoi gian cap nhat
    private LocalDateTime updatedAt;
}

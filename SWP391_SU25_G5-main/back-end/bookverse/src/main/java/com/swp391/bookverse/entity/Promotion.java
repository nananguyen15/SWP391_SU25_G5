package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * Entity map voi bang promotions (luu thong tin khuyen mai)
 */
@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title; // Tieu de khuyen mai

    @Column(columnDefinition = "TEXT")
    private String description; // Noi dung khuyen mai

    @Column(nullable = false)
    private LocalDateTime startDate; // Ngay bat dau

    @Column(nullable = false)
    private LocalDateTime endDate; // Ngay ket thuc

    @Column(nullable = false)
    private Boolean active; // Trang thai (true = dang hoat dong)

    @Column(nullable = false, length = 50)
    private String type; // Loai khuyen mai (discount, voucher...)
}

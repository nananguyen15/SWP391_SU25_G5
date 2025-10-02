package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 */
@Entity
@Table(name = "customer_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Moi review thuoc ve mot khach hang
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    // Moi review lien ket voi mot sach
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private int rating; // So sao tu 1 -> 5

    @Column(columnDefinition = "TEXT")
    private String comment; // Noi dung review

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

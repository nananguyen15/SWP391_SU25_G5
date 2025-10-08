package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: Entity class that maps to the "reviews" table in the database.
 *                Represents a customer's review for a specific book.
 *                Includes rating, comment, and timestamps for creation and update.
 * @Table: reviews
 * @Package: com.swp391.bookverse.entity
 */
@Entity
@Table(name = "reviews")
@Data
public class CustomerReview {

    /**
     * Primary key - auto-generated unique ID for each review record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID of the customer who created the review (foreign key referencing users table).
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * ID of the book that this review belongs to (foreign key referencing books table).
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * Rating value given by the customer (range: 1–5).
     */
    @Column(name = "rating")
    private Integer rating;

    /**
     * Text content of the review or feedback written by the customer.
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Timestamp marking when the review was created (auto-filled by Hibernate).
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Uncomment this field if you need to track the last update time of the review
    // @UpdateTimestamp
    // @Column(name = "updated_at")
    // private LocalDateTime updatedAt;
}

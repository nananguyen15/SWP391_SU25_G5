package com.swp391.bookverse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @Author: NhaNT9324W
 * Entity class that maps to the "notification" table in the database.
 * This entity stores all notifications related to customers.
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerNotification {

    /**
     * Primary key of the notification entity.
     * This represents the unique ID of each notification (not the user ID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Reference to the User entity that owns this notification.
     * Uses a Many-to-One relationship since one user can have multiple notifications.
     * The field is ignored in JSON serialization to prevent recursive data exposure.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
            // columnDefinition = "VARCHAR(36)" // optional, depends on user ID type
    )
    private User user;

    /**
     * The main content or message body of the notification.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * Type of notification (e.g., INFO, PROMOTION, SYSTEM, etc.).
     */
    @Column(length = 50)
    private String type;

    /**
     * Indicates whether the notification has been read by the user.
     * Default value is false.
     */
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    /**
     * The timestamp indicating when the notification was created.
     */
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * Automatically set the createdAt field to the current time before persisting,
     * if it is not already set.
     */
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}

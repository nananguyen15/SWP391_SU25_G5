package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique = true, nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    String name;
    String email;
    LocalDate birthDate;
    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    Set<String> roles; // Set of roles assigned to the user, e.g., "USER", "ADMIN", "MODERATOR", etc.
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.UNVERIFIED;

    private String verificationToken;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

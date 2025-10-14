package com.swp391.bookverse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Data
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    String id;

    String username;
    String password;
    String email;

    @Column(name = "fullname")
    String name;

    String phone;
    String address;
    @Column(length = 500)
    String image;
    boolean active;

    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    Set<String> roles;
}
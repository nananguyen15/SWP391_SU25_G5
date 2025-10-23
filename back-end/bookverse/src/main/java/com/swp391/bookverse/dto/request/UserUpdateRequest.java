package com.swp391.bookverse.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @Author huangdat
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class UserUpdateRequest {
//    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "USERNAME_INVALID") // Only allows letters, numbers, and underscores
//    @Size(min = 8, max = 32, message = "USERNAME_INVALID")
//    String username;
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "USERNAME_INVALID") // Only allows letters, numbers, and underscores
    @Size(min = 8, max = 16, message = "PASSWORD_INVALID")
    String password;
//    @Email(message = "EMAIL_INVALID")
//    String email;
    @Size(max = 255, message = "FULLNAME_INVALID")
    String name;
    @Pattern(regexp = "0[3-9]\\d{8,9}", message = "PHONE_INVALID") // Matches Vietnamese phone numbers. E.g., 0912345678
    String phone;
    String address;
    String image;
}

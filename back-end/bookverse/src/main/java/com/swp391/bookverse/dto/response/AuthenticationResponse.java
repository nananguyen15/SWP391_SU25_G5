package com.swp391.bookverse.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class AuthenticationResponse {
    String token; // JWT token for authentication
    boolean authenticated;
}

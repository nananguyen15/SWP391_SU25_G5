package com.swp391.bookverse.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
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
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @Size(min = 3, message = "NAME_INVALID")
    String name;
    @Email(message = "EMAIL_INVALID")
    String email;
    @Past(message = "BIRTH_DATE_INVALID")
    LocalDate birthDate;

}

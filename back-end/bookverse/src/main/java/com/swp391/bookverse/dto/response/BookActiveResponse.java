package com.swp391.bookverse.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author huangdat
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Set default access level for fields to private
public class BookActiveResponse {
    Long id;
    String title;
    Boolean active;
}

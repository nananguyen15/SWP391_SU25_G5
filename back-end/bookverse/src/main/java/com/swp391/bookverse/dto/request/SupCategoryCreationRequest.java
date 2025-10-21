package com.swp391.bookverse.dto.request;

import jakarta.persistence.Column;
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
public class SupCategoryCreationRequest {
    String name;
    @Column(nullable = false)
    Boolean active;
}

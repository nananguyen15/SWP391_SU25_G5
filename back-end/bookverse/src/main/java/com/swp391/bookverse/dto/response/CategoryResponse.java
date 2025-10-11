package com.swp391.bookverse.dto.response;

import com.swp391.bookverse.entity.SubCategory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CategoryResponse {
    String id;
    String name;
    String description;

    public CategoryResponse(com.swp391.bookverse.entity.SubCategory category) {
        this.id = category.getId() == null ? null : String.valueOf(category.getId());
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public static CategoryResponse from(SubCategory subCategory) {
        return subCategory == null ? null : new CategoryResponse(subCategory);
    }
}

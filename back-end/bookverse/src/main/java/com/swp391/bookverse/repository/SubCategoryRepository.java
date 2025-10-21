package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.entity.SupCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}

package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @Author nhung
 */

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByName(String name);
}

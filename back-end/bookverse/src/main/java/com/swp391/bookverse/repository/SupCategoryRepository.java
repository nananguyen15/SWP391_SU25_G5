package com.swp391.bookverse.repository;


import com.swp391.bookverse.entity.SupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author nhung
 */

@Repository
public interface SupCategoryRepository extends JpaRepository<SupCategory, Long> {
    SupCategory findByName(String name);
}


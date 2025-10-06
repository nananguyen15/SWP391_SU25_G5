package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author huangdat
 */

public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByNameContainingIgnoreCase(String keyword);
}
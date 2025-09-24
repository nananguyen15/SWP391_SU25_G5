package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
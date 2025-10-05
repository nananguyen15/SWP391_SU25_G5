package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author huangdat
 */

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author huangdat
 */

public interface BookRepository extends JpaRepository<Book, Long> {
}
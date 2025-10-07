package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @Author huangdat
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    List<Book> findByCategory_Id(Long categoryId);


    // For customer search - only books with stock > 0
    Page<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStockQuantityGreaterThan(
            String title, String description, int stockQuantity, Pageable pageable);

    // For admin search - all books with pagination
    Page<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description, Pageable pageable);



}
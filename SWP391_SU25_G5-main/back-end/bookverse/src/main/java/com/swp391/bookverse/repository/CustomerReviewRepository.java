package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Repository thao tac voi bang reviews
 */
public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {

    List<CustomerReview> findByCommentContainingIgnoreCase(String keyword);

    // Lay tat ca review cua 1 cuon sach
    List<CustomerReview> findByBookId(Long bookId);

    // Lay tat ca review cua 1 khach hang
    List<CustomerReview> findByCustomerId(Long customerId);
}

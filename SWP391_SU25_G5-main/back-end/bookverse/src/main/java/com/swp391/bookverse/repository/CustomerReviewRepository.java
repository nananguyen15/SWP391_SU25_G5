package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: NhaNT9324W
 */
@Repository
public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {

    List<CustomerReview> findByBookId(Long bookId);

    List<CustomerReview> findByCustomerId(Long customerId);
}

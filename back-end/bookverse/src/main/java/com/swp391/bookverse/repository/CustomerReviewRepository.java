package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // <-- THÊM IMPORT NÀY
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @Author: NhaNT9324W
 * @CreatedDate: 2025-10-08
 * @Description: Repository interface for managing CustomerReview entities.
 * Provides standard CRUD operations, custom queries, and support
 * for dynamic specification-based queries for admin filtering.
 * @Package: com.swp391.bookverse.repository
 */
@Repository
public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long>, JpaSpecificationExecutor<CustomerReview> {

    /**
     * Retrieve all reviews created by a specific customer.
     * @param userId ID of the customer.
     * @return List of CustomerReview objects.
     */
    List<CustomerReview> findByUserId(String userId);

    /**
     * Find a review by its ID and the customer's ID.
     * Used to ensure that the review belongs to the requesting user.
     * @param id Review ID.
     * @param userId Customer ID.
     * @return Optional containing the CustomerReview if found.
     */
    Optional<CustomerReview> findByIdAndUserId(Long id, String userId);

    /**
     * Retrieve all reviews written for a specific book.
     * @param bookId ID of the book.
     * @return List of CustomerReview objects.
     */
    List<CustomerReview> findByBookId(Long bookId);

    /**
     * Check if a specific customer has already reviewed a particular book.
     * @param userId ID of the customer.
     * @param bookId ID of the book.
     * @return true if the review exists, false otherwise.
     */
    boolean existsByUserIdAndBookId(String userId, Long bookId);
}
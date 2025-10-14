package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.repository.CustomerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles business logic for review management by administrators and staff.
 * This service provides functionalities to search, filter, and delete any review
 * in the system, reflecting the elevated permissions of admin roles.
 *
 * @Author: NhaNT9324W
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final CustomerReviewRepository customerReviewRepository;

    /**
     * Searches and filters reviews based on a dynamic set of criteria.
     * This method constructs a JPA Specification to build a query that can filter by
     * a keyword in the comment, a specific book ID, or a specific user ID.
     *
     * @param keyword The keyword to search for within the review's comment. Can be null or empty.
     * @param bookId The ID of the book to filter reviews by. Can be null.
     * @param userId The ID of the user (customer) to filter reviews by. Can be null or empty.
     * @return A list of {@link CustomerReview} entities that match the specified criteria,
     * ordered by the creation date in descending order.
     */
    public List<CustomerReview> searchAndFilterReviews(String keyword, Long bookId, String userId) {
        Specification<CustomerReview> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add predicate for keyword search in the 'comment' field if the keyword is provided.
            if (StringUtils.hasText(keyword)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("comment")), "%" + keyword.toLowerCase() + "%"));
            }

            // Add predicate to filter by book ID if bookId is provided.
            if (bookId != null) {
                predicates.add(criteriaBuilder.equal(root.get("book").get("id"), bookId));
            }

            // Add predicate to filter by user ID if userId is provided.
            if (StringUtils.hasText(userId)) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            }

            // Order the results by the 'createdAt' timestamp, newest first.
            query.orderBy(criteriaBuilder.desc(root.get("createdAt")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return customerReviewRepository.findAll(spec);
    }

    /**
     * Deletes a review from the system based on its unique identifier.
     * It first verifies the existence of the review before proceeding with the deletion.
     *
     * @param reviewId The ID of the review to be deleted.
     * @throws AppException if no review is found with the given ID, with an error code of REVIEW_NOT_FOUND.
     */
    public void deleteReview(Long reviewId) {
        // Find the review by its ID or throw a custom exception if it does not exist.
        CustomerReview review = customerReviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        // Delete the found review entity.
        customerReviewRepository.delete(review);
    }
}
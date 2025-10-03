package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.repository.CustomerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Service xu ly logic review danh cho Admin/Staff
 * - UC-70: View all reviews
 * - UC-71: Search reviews
 * - UC-72: Filter reviews
 * - UC-73: Delete review
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final CustomerReviewRepository reviewRepository;

    // Lay tat ca review
    public List<CustomerReview> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Tim kiem/loc review theo keyword, bookId, customerId
    public List<CustomerReview> searchReviews(String keyword, Long bookId, Long customerId) {
        if (keyword != null && !keyword.isEmpty()) {
            return reviewRepository.findByCommentContainingIgnoreCase(keyword);
        }
        if (bookId != null) {
            return reviewRepository.findByBookId(bookId);
        }
        if (customerId != null) {
            return reviewRepository.findByCustomerId(customerId);
        }
        return reviewRepository.findAll();
    }

    // Xoa review theo ID
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import com.swp391.bookverse.repository.CustomerReviewRepository;
import com.swp391.bookverse.service.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
/**
 * @Author: NhaNT9324W
 */
public class CustomerReviewServiceImpl implements CustomerReviewService {

    private final CustomerReviewRepository reviewRepository;

    @Override
    public CustomerReview createReview(CustomerReview review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<CustomerReview> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public List<CustomerReview> getReviewsByCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    @Override
    public CustomerReview updateReview(Long reviewId, CustomerReview updatedReview) {
        return reviewRepository.findById(reviewId).map(review -> {
            review.setRating(updatedReview.getRating());
            review.setComment(updatedReview.getComment());
            review.setUpdatedAt(LocalDateTime.now());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

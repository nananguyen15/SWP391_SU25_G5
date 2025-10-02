package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.CustomerReview;
import java.util.List;

/**
 * @Author: NhaNT9324W
 */
public interface CustomerReviewService {

    CustomerReview createReview(CustomerReview review);

    List<CustomerReview> getReviewsByBook(Long bookId);

    List<CustomerReview> getReviewsByCustomer(Long customerId);

    CustomerReview updateReview(Long reviewId, CustomerReview updatedReview);

    void deleteReview(Long reviewId);
}

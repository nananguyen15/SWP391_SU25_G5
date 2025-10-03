package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: NhaNT9324W
 * Repository thao tac du lieu khuyen mai
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // Tim danh sach khuyen mai dang active
    List<Promotion> findByActiveTrue();
}

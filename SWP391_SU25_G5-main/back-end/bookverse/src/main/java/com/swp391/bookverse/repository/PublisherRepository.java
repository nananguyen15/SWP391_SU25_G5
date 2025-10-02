package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
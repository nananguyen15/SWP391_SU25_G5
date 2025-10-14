package com.swp391.bookverse.repository;

import com.swp391.bookverse.entity.CustomerNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link CustomerNotification} entities.
 * Provides methods to query notifications based on user information.
 *
 * This interface extends {@link JpaRepository}, giving access to
 * built-in CRUD operations (save, findAll, delete, etc.).
 */
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Long> {

    /**
     * Retrieves all notifications belonging to a specific user using derived query method.
     *
     * @param userId the ID of the user whose notifications are being retrieved
     * @return a list of {@link CustomerNotification} objects for the given user
     */
    List<CustomerNotification> findByUser_Id(String userId);

    /**
     * Retrieves all notifications for a specific user using a custom JPQL query.
     * Functionally similar to {@link #findByUser_Id(String)}, but implemented via explicit query.
     *
     * @param userId the ID of the user whose notifications are being retrieved
     * @return a list of {@link CustomerNotification} objects for the given user
     */
    @Query("SELECT n FROM CustomerNotification n WHERE n.user.id = :userId")
    List<CustomerNotification> findByUserId(@Param("userId") String userId);

    /**
     * Finds a specific notification by its ID and the user's ID.
     * Used to ensure that the notification belongs to the correct user before updating or viewing.
     *
     * @param id the ID of the notification
     * @param userId the ID of the user who owns the notification
     * @return an {@link Optional} containing the matching {@link CustomerNotification}, or empty if not found
     */
    Optional<CustomerNotification> findByIdAndUser_Id(Long id, String userId);
}

package edu.university_connect.repository;

import edu.university_connect.domain.entity.Event;
import edu.university_connect.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e.attendees FROM Event e WHERE e.id = :eventId ")
    Page<User> findAllRsvpAttendeeByPage(@Param("eventId") Long eventId, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.createdBy=:username")
    Page<Event> findPageByUser(String username, Pageable pageable);
}

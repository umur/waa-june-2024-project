package com.waa.project.repository;

import com.waa.project.entity.Event;
import org.springframework.data.repository.ListCrudRepository;

public interface EventRepository extends ListCrudRepository<Event, Long> {
}

package com.waa.project.repository;

import com.waa.project.entity.Course;
import org.springframework.data.repository.ListCrudRepository;

public interface CourseRepository extends ListCrudRepository<Course, Long> {
}

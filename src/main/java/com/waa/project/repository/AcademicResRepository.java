package com.waa.project.repository;

import com.waa.project.entity.AcademicResource;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicResRepository extends ListCrudRepository<AcademicResource, Long> {
}

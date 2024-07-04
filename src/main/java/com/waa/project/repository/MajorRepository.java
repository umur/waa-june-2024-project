package com.waa.project.repository;

import com.waa.project.entity.Major;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends ListCrudRepository<Major, Long> {
}

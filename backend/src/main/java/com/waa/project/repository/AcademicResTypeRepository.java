package com.waa.project.repository;

import com.waa.project.entity.AcademicResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicResTypeRepository extends JpaRepository<AcademicResourceType, Long> {
}

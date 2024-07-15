package com.waa.project.repository;

import com.waa.project.entity.AcademicResource;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicResRepository extends ListCrudRepository<AcademicResource, Long> {
    List<AcademicResource> findAcademicResourceByResourceTypeName(String resname);
}

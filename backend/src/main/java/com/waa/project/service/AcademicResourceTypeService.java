package com.waa.project.service;

import com.waa.project.dto.AcademicResourceTypeDto;
import com.waa.project.dto.requests.AcademicResourceTypeRequest;
import com.waa.project.dto.responses.AcademicResourceTypeResponse;
import com.waa.project.entity.AcademicResourceType;

import java.util.List;

public interface AcademicResourceTypeService {
    List<AcademicResourceTypeDto> getAllCategories();

    AcademicResourceTypeDto getAcademicResourceType(Long feedId);

    AcademicResourceTypeResponse save(AcademicResourceTypeRequest request);

    AcademicResourceTypeResponse update(AcademicResourceTypeRequest resType, Long fid);

    List<AcademicResourceType> delete(Long fid);
}

package com.waa.project.service;

import com.waa.project.dto.AcademicResourceTypeDto;

import java.util.List;

public interface AcademicResourceTypeService {
    List<AcademicResourceTypeDto> getAllCategories();

    AcademicResourceTypeDto getAcademicResourceType(Long feedId);

    String save(AcademicResourceTypeDto resType);

    String update(AcademicResourceTypeDto resType, Long fid);

    String delete(Long fid);
}

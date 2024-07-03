package com.waa.project.service;

import com.waa.project.dto.AcademicResourceDto;

import java.util.List;

public interface AcademicResService {
    List<AcademicResourceDto> getAllAcademicRes();

    AcademicResourceDto getAcademicResource(Long feedId);

    String save(AcademicResourceDto dto);

    String update(AcademicResourceDto dto, Long fid);

    String delete(Long fid);
}

package com.waa.project.service;

import com.waa.project.dto.AcademicResourceDto;
import com.waa.project.dto.AcademicResourceRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AcademicResService {
    List<AcademicResourceDto> getAllAcademicRes();

    AcademicResourceDto getAcademicResource(Long feedId);

    String save(AcademicResourceRequest dto, MultipartFile file);

    String update(AcademicResourceDto dto, Long fid);

    String delete(Long fid);

    List<AcademicResourceDto> searchByResourceName(String resname);
}

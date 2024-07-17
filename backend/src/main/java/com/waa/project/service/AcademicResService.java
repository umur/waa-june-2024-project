package com.waa.project.service;

import com.waa.project.dto.requests.AcademicResourceRequest;
import com.waa.project.dto.responses.AcademicResourceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AcademicResService {
    List<AcademicResourceResponse> getAllAcademicRes();

    AcademicResourceResponse getAcademicResource(Long feedId);

    AcademicResourceResponse save(AcademicResourceRequest request, MultipartFile file);

    AcademicResourceResponse update(AcademicResourceRequest request, Long fid, MultipartFile file);

    List<AcademicResourceResponse> delete(Long fid);

    List<AcademicResourceResponse> searchByResourceName(String resname);
}

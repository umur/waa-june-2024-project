package com.waa.project.service;

import com.waa.project.dto.MajorDto;
import com.waa.project.dto.responses.MajorResponseDto;

import java.util.List;

public interface MajorService {
    List<MajorResponseDto> getAllMajor();

    MajorResponseDto getMajor(Long Id);

    MajorDto save(MajorDto major);

    MajorDto update(MajorDto param, Long Id);

    String delete(Long Id);

}

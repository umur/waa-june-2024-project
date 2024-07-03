package com.waa.project.service;

import com.waa.project.dto.MajorDto;

import java.util.List;

public interface MajorService {
    List<MajorDto> getAllMajor();

    MajorDto getMajor(Long Id);

    String save(MajorDto param);

    String update(MajorDto param, Long Id);

    String delete(Long Id);

}

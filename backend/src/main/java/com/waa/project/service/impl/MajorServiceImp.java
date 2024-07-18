package com.waa.project.service.impl;

import com.waa.project.dto.MajorDto;
import com.waa.project.dto.requests.EventRequestDto;
import com.waa.project.dto.responses.MajorResponseDto;
import com.waa.project.entity.Event;
import com.waa.project.entity.Major;
import com.waa.project.repository.MajorRepository;
import com.waa.project.service.MajorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MajorServiceImp implements MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MajorResponseDto> getAllMajor() {
        return majorRepository.findAll().stream().map(major -> modelMapper.map(major, MajorResponseDto.class)).toList();
    }

    @Override
    public MajorResponseDto getMajor(Long majorId) {
        Major major = majorRepository.findById(majorId).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(major, MajorResponseDto.class);
    }

    @Override
    public MajorDto save(MajorDto major) {
        Major dataToSave = modelMapper.map(major, Major.class);
        return modelMapper.map(majorRepository.save(dataToSave), MajorDto.class);
    }

    @Override
    public MajorDto update(MajorDto major, Long fid) {
        Major majorToUpdate = majorRepository.findById(fid)
                                             .orElseThrow(
                                                     () -> new NoSuchElementException("No major was found."));
        majorToUpdate.setName(major.getName());
        majorToUpdate.setDescription(major.getDescription());
        return modelMapper.map(majorToUpdate, MajorDto.class);
    }

    @Override
    public String delete(Long fid) {
        majorRepository.deleteById(fid);
        return "Major is deleted.";
    }
}

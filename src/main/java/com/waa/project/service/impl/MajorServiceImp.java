package com.waa.project.service.impl;

import com.waa.project.dto.MajorDto;
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
    public List<MajorDto> getAllMajor() {
        List<MajorDto> list = new ArrayList<>();
        majorRepository.findAll().forEach(feed -> list.add(modelMapper.map(feed, MajorDto.class)));
        return list;
    }

    @Override
    public MajorDto getMajor(Long majorId) {
        Major major = majorRepository.findById(majorId).get();
        return modelMapper.map(major, MajorDto.class);
    }

    @Override
    public String save(MajorDto major) {
        Major dataToSave = modelMapper.map(major, Major.class);
        majorRepository.save(dataToSave);
        return "Major saved successfully.";
    }

    @Override
    public String update(MajorDto major, Long fid) {
        Major majorToUpdate = majorRepository.findById(fid)
                                             .orElseThrow(
                                                     () -> new NoSuchElementException("No major was found."));
        majorToUpdate.setName(major.getName());
        majorToUpdate.setDescription(major.getDescription());
        majorRepository.save(majorToUpdate);
        return "Major is updated.";
    }

    @Override
    public String delete(Long fid) {
        majorRepository.deleteById(fid);
        return "Major is deleted.";
    }
}

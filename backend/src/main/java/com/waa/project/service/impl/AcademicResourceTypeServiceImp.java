package com.waa.project.service.impl;

import com.waa.project.dto.AcademicResourceTypeDto;
import com.waa.project.dto.requests.AcademicResourceTypeRequest;
import com.waa.project.dto.responses.AcademicResourceTypeResponse;
import com.waa.project.entity.AcademicResourceType;
import com.waa.project.repository.AcademicResTypeRepository;
import com.waa.project.service.AcademicResourceTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AcademicResourceTypeServiceImp implements AcademicResourceTypeService {

    @Autowired
    private AcademicResTypeRepository academicResTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AcademicResourceTypeDto> getAllCategories() {
        List<AcademicResourceTypeDto> results = new ArrayList<>();
        academicResTypeRepository.findAll().forEach(feed -> results.add(modelMapper.map(
                feed, AcademicResourceTypeDto.class)));

        return results;
    }

    @Override
    public AcademicResourceTypeDto getAcademicResourceType(Long id) {
        return modelMapper.map(academicResTypeRepository.findById(id).get(), AcademicResourceTypeDto.class);
    }

    @Override
    public AcademicResourceTypeResponse save(AcademicResourceTypeRequest request) {
        AcademicResourceType academicResourceType = modelMapper.map(request, AcademicResourceType.class);
        academicResTypeRepository.save(academicResourceType);
        return modelMapper.map(academicResourceType, AcademicResourceTypeResponse.class);
    }

    @Override
    public AcademicResourceTypeResponse update(AcademicResourceTypeRequest request, Long fid) {
        AcademicResourceType dataToUpdate = academicResTypeRepository.findById(fid)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "No feedback was found."));
        dataToUpdate.setName(request.getName());
        academicResTypeRepository.save(dataToUpdate);
        return modelMapper.map(academicResTypeRepository.findAll(), AcademicResourceTypeResponse.class);
    }

    @Override
    public List<AcademicResourceType> delete(Long fid) {
        academicResTypeRepository.deleteById(fid);
        return academicResTypeRepository.findAll();
    }
}

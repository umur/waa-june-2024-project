package com.waa.project.service.impl;

import com.waa.project.dto.AcademicResourceDto;
import com.waa.project.dto.AcademicResourceRequest;
import com.waa.project.entity.AcademicResource;
import com.waa.project.entity.AcademicResourceType;
import com.waa.project.repository.AcademicResRepository;
import com.waa.project.repository.AcademicResTypeRepository;
import com.waa.project.service.AcademicResService;
import com.waa.project.service.FileService;
import com.waa.project.util.FileSaveLocations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicResServiceImp implements AcademicResService {

    @Autowired
    private AcademicResRepository academicResRepository;

    @Autowired
    private AcademicResTypeRepository academicResTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<AcademicResourceDto> getAllAcademicRes() {
        List<AcademicResourceDto> list = new ArrayList<>();
        academicResRepository.findAll().forEach(feed -> list.add(modelMapper.map(feed, AcademicResourceDto.class)));
        return list;
    }

    @Override
    public AcademicResourceDto getAcademicResource(Long feedId) {
        AcademicResource res = academicResRepository.findById(feedId).get();
        return modelMapper.map(res, AcademicResourceDto.class);
    }

    @Override
    public String save(AcademicResourceRequest res, MultipartFile file) {
        AcademicResource resToSave = modelMapper.map(res, AcademicResource.class);

        AcademicResourceType resCategory =
                academicResTypeRepository.findById(res.getResourceId()).get();

        if (file != null && !file.isEmpty()) {
            String folder = resCategory.getName().replace(" ", "_");
            String filePath = fileService.saveFile(file, FileSaveLocations.academicResource(folder));
            resToSave.setFile(filePath);
        }
        resToSave.setResourceType(resCategory);
        academicResRepository.save(resToSave);
        return "AcademicResource saved successfully.";
    }

    @Override
    public String update(AcademicResourceDto res, Long fid) {
        AcademicResource feedToUpdate = academicResRepository.findById(fid).orElse(null);

        AcademicResourceType academicResourceType =
                academicResTypeRepository.findById(res.getResourceType().getId()).get();
        if (academicResourceType != null)
            feedToUpdate.setResourceType(academicResourceType);

        feedToUpdate.setName(res.getName());

        academicResRepository.save(feedToUpdate);
        return "AcademicResource is updated.";

    }

    @Override
    public String delete(Long fid) {
        academicResRepository.deleteById(fid);
        return "AcademicResource is deleted.";
    }

    @Override
    public List<AcademicResourceDto> searchByResourceName(String resname) {
        List<AcademicResourceDto> result = new ArrayList<>();
        List<AcademicResource> res = academicResRepository.findAcademicResourceByResourceTypeName(resname);
        res.forEach(ar -> result.add(modelMapper.map(ar, AcademicResourceDto.class)));
        return result;
    }
}
package com.waa.project.service.impl;

import com.waa.project.dto.requests.AcademicResourceRequest;
import com.waa.project.dto.responses.AcademicResourceResponse;
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
    public List<AcademicResourceResponse> getAllAcademicRes() {
        List<AcademicResourceResponse> list = new ArrayList<>();
        academicResRepository.findAll().forEach(feed -> list.add(modelMapper.map(feed, AcademicResourceResponse.class)));
        return list;

    }

    @Override
    public AcademicResourceResponse getAcademicResource(Long feedId) {
        AcademicResource res = academicResRepository.findById(feedId).get();
        return modelMapper.map(res, AcademicResourceResponse.class);
    }

    @Override
    public AcademicResourceResponse save(AcademicResourceRequest res, MultipartFile file) {
        AcademicResource dataToSave = modelMapper.map(res, AcademicResource.class);
        AcademicResourceType resCategory =
                academicResTypeRepository.findById(res.getResourceId()).get();

        if (file != null && !file.isEmpty()) {
            String folder = resCategory.getName().replace(" ", "_");
            String filePath = fileService.saveFile(file, FileSaveLocations.academicResource(folder));
            dataToSave.setFile(filePath);
        }
        dataToSave.setResourceType(resCategory);
        dataToSave.setName(res.getName());
        dataToSave.setBody(res.getBody());

        academicResRepository.save(dataToSave);

        return modelMapper.map(dataToSave, AcademicResourceResponse.class);
    }


    @Override
    public AcademicResourceResponse update(AcademicResourceRequest res, Long fid, MultipartFile file) {
        AcademicResource dataToUpdate = academicResRepository.findById(fid).orElse(null);

        AcademicResourceType academicResourceType =
                academicResTypeRepository.findById(res.getResourceId()).get();
        if (academicResourceType != null)
            dataToUpdate.setResourceType(academicResourceType);

        dataToUpdate.setName(res.getName());
        dataToUpdate.setBody(res.getBody());

        return modelMapper.map(academicResRepository.save(dataToUpdate), AcademicResourceResponse.class);
    }

    @Override
    public List<AcademicResourceResponse> delete(Long fid) {
        academicResRepository.deleteById(fid);

        List<AcademicResourceResponse> list = new ArrayList<>();
        academicResRepository.findAll().forEach(feed -> list.add(modelMapper.map(feed, AcademicResourceResponse.class)));
        return list;
    }

    @Override
    public List<AcademicResourceResponse> searchByResourceName(String resname) {
        List<AcademicResourceResponse> result = new ArrayList<>();
        List<AcademicResource> res = academicResRepository.findAcademicResourceByResourceTypeName(resname);
        res.forEach(ar -> result.add(modelMapper.map(ar, AcademicResourceResponse.class)));
        return result;
    }
}

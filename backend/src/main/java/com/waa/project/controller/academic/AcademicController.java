package com.waa.project.controller.academic;

import com.waa.project.dto.requests.AcademicResourceRequest;
import com.waa.project.dto.responses.AcademicResourceResponse;
import com.waa.project.service.AcademicResService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class AcademicController {

    @Autowired
    private AcademicResService academicResService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping({"/academic"})//, "/students/academic"})
    public List<AcademicResourceResponse> getAllResources() {
        return academicResService.getAllAcademicRes();
    }

    @GetMapping({"/academic/{Id}"})//, "/students/academic/{Id}"})
    public AcademicResourceResponse getResource(@PathVariable Long Id) {

        return academicResService.getAcademicResource(Id);
    }

    @PutMapping({"/academic/{Id}"})//, "/students/academic/{Id}"})
    public ResponseEntity<AcademicResourceResponse> update(
            @Valid @RequestPart(value = "formdata") AcademicResourceRequest dto,
            @PathVariable Long Id,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(academicResService.update(dto, Id, file), HttpStatus.CREATED);
    }

    @PostMapping(value = {"/academic"})//, "/students/academic"}, consumes = "multipart/form-data")
    public ResponseEntity<AcademicResourceResponse> save(@Valid @RequestPart(value = "formdata") AcademicResourceRequest request,
                                                         @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(academicResService.save(request, file), HttpStatus.CREATED);
    }

    @GetMapping({"/admins/academic/search", "/students/academic/search"})
    public List<AcademicResourceResponse> search(@RequestParam String search) {
        return academicResService.searchByResourceName(search);
    }

    @DeleteMapping({"/academic/{Id}"})//, "/students/academic/{Id}"})
    public List<AcademicResourceResponse> delete(@PathVariable Long Id) {

        return academicResService.delete(Id);
    }
}

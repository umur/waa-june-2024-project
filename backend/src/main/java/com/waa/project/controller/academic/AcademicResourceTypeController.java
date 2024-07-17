package com.waa.project.controller.academic;

import com.waa.project.dto.AcademicResourceTypeDto;
import com.waa.project.dto.requests.AcademicResourceTypeRequest;
import com.waa.project.dto.responses.AcademicResourceTypeResponse;
import com.waa.project.entity.AcademicResourceType;
import com.waa.project.service.AcademicResourceTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class AcademicResourceTypeController {

    @Autowired
    private AcademicResourceTypeService academicResourceTypeService;

    @GetMapping({"/academicresources"})//, "/students/academicresources"})
    public List<AcademicResourceTypeDto> getAllResourcesTypes() {
        return academicResourceTypeService.getAllCategories();
    }

    @GetMapping({"/academicresources/{catId}"})//, "/students/academicresources/{catId}"})
    public AcademicResourceTypeDto getResourceType(@PathVariable Long catId) {
        return academicResourceTypeService.getAcademicResourceType(catId);
    }

    @PutMapping({"/academicresources/{catId}"})//, "/students/academicresources/{catId}"})
    public ResponseEntity<AcademicResourceTypeResponse> update(
            @Valid @RequestBody AcademicResourceTypeRequest request,
            @PathVariable Long catId
    ) {
        return new ResponseEntity<>(academicResourceTypeService.update(request, catId), HttpStatus.CREATED);
    }

    @PostMapping({"/academicresources"})//, "/students/academicresources"})
    public ResponseEntity<AcademicResourceTypeResponse> save(
            @Valid @RequestBody AcademicResourceTypeRequest request) {
        return new ResponseEntity<>(academicResourceTypeService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping({"/academicresources/{catId}"})//, "/students/academicresources/{catId}"})
    public List<AcademicResourceType> delete(@PathVariable Long catId) {
        return academicResourceTypeService.delete(catId);
    }
}

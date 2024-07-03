package com.waa.project.controller.academic;

import com.waa.project.dto.AcademicResourceTypeDto;
import com.waa.project.service.AcademicResourceTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class AcademicResourceTypeController {

    @Autowired
    private AcademicResourceTypeService academicResourceTypeService;

    @GetMapping({"/admins/academicresources", "/students/academicresources"})
    public List<AcademicResourceTypeDto> getAllResourcesTypes() {
        return academicResourceTypeService.getAllCategories();
    }

    @GetMapping({"/admins/academicresources/{catId}", "/students/academicresources/{catId}"})
    public AcademicResourceTypeDto getResourceType(@PathVariable Long catId) {
        return academicResourceTypeService.getAcademicResourceType(catId);
    }

    @PutMapping({"/admins/academicresources/{catId}", "/students/academicresources/{catId}"})
    public String update(
            @RequestBody AcademicResourceTypeDto dto,
            @PathVariable Long catId
                        ) {
        return academicResourceTypeService.update(dto, catId);
    }

    @PostMapping({"/admins/academicresources", "/students/academicresources"})
    public String save(@RequestBody AcademicResourceTypeDto dto) {
        return academicResourceTypeService.save(dto);
    }

    @DeleteMapping({"/admins/academicresources/{catId}", "/students/academicresources/{catId}"})
    public String delete(@PathVariable Long catId) {
        return academicResourceTypeService.delete(catId);
    }
}

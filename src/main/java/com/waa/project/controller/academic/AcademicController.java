package com.waa.project.controller.academic;

import com.waa.project.dto.AcademicResourceDto;
import com.waa.project.service.AcademicResService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"/admins/academic", "/students/academic"})
    public List<AcademicResourceDto> getAllResources() {

        return academicResService.getAllAcademicRes();
    }

    @GetMapping({"/admins/academic/{Id}", "/students/academic/{Id}"})
    public AcademicResourceDto getResource(@PathVariable Long Id) {

        return academicResService.getAcademicResource(Id);
    }

    @PutMapping({"/admins/academic/{Id}", "/students/academic/{Id}"})
    public String update(
            @RequestBody AcademicResourceDto dto, @PathVariable Long Id
    ) {
        return academicResService.update(dto, Id);
    }

    @PostMapping(value = {"/admins/academic", "/students/academic"}, consumes = "multipart/form-data")
    public String save(@RequestBody AcademicResourceDto dto,
                       @RequestPart("file") MultipartFile file) {
        System.out.println("file ===" + file);
        return academicResService.save(dto);
    }

    @GetMapping({"/admins/academic/search", "/students/academic/search"})
    public List<AcademicResourceDto> search(@RequestParam String search) {
        return academicResService.searchByResourceName(search);
    }

    @DeleteMapping({"/admins/academic/{Id}", "/students/academic/{Id}"})
    public String delete(@PathVariable Long Id) {

        return academicResService.delete(Id);
    }
}

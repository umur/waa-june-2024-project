package com.waa.project.controller.academic;

import com.waa.project.dto.AcademicResourceDto;
import com.waa.project.service.AcademicResService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping({"/admins/academic", "/students/academic"})
    public String save(@RequestBody AcademicResourceDto dto) {

        return academicResService.save(dto);
    }

    @DeleteMapping({"/admins/academic/{Id}", "/students/academic/{Id}"})
    public String delete(@PathVariable Long Id) {

        return academicResService.delete(Id);
    }
}

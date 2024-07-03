package com.waa.project.controller.academic;

import com.waa.project.dto.MajorDto;
import com.waa.project.service.MajorService;
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
    private MajorService majorService;

    @GetMapping("/majors")
    public List<MajorDto> getAllMajor() {

        return majorService.getAllMajor();
    }

    @GetMapping("/majors/{Id}")
    public MajorDto getMajor(@PathVariable Long Id) {

        return majorService.getMajor(Id);
    }

    @PutMapping("/admins/majors/{Id}")
    public String update(
            @RequestBody MajorDto dto, @PathVariable Long Id
                        ) {
        return majorService.update(dto, Id);
    }

    @PostMapping("/admins/majors")
    public String save(@RequestBody MajorDto dto) {
        return majorService.save(dto);
    }

    @DeleteMapping("/admins/majors/{Id}")
    public String delete(@PathVariable Long Id) {

        return majorService.delete(Id);
    }
}

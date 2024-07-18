package com.waa.project.controller.major;

import com.waa.project.dto.MajorDto;
import com.waa.project.dto.responses.MajorResponseDto;
import com.waa.project.service.MajorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("/majors")
    public ResponseEntity<List<MajorResponseDto>> getAllMajor() {
        return new ResponseEntity<>(majorService.getAllMajor(), HttpStatus.OK);
    }

    @GetMapping("/majors/{Id}")
    public ResponseEntity<MajorResponseDto> getMajor(@PathVariable Long Id) {
        return new ResponseEntity<>(majorService.getMajor(Id), HttpStatus.OK);
    }

    @PutMapping("/admins/majors/{Id}")
    public ResponseEntity<MajorDto> update(@Valid
            @RequestBody MajorDto major, @PathVariable Long Id
                        ) {
        return new ResponseEntity<>(majorService.update(major, Id), HttpStatus.OK);
    }

    @PostMapping("/admins/majors")
    public ResponseEntity<MajorDto> save( @Valid @RequestBody MajorDto major) {

        return new ResponseEntity<>(majorService.save(major), HttpStatus.CREATED);
    }

    @DeleteMapping("/admins/majors/{Id}")
    public ResponseEntity<String> delete(@PathVariable Long Id) {
        return new ResponseEntity<>(majorService.delete(Id), HttpStatus.OK);
    }
}

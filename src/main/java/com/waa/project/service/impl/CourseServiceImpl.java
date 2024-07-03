package com.waa.project.service.impl;

import com.waa.project.dto.responses.CourseResponseDTO;
import com.waa.project.entity.Course;
import com.waa.project.repository.CourseRepository;
import com.waa.project.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final ModelMapper      courseMapper;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(ModelMapper courseMapper, CourseRepository courseRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(course -> courseMapper.map(course, CourseResponseDTO.class))
                              .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            return courseMapper.map(course, CourseResponseDTO.class);
        } else {
            return null;
        }
    }
}

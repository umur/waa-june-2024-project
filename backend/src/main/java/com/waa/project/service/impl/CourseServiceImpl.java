package com.waa.project.service.impl;

import com.waa.project.dto.responses.CourseResponseDto;
import com.waa.project.entity.Course;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.CourseRepository;
import com.waa.project.service.CourseService;
import com.waa.project.util.CourseErrorMessage;
import com.waa.project.util.EventsErrorMessage;
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
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(course -> courseMapper.map(course, CourseResponseDto.class))
                              .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            return courseMapper.map(course, CourseResponseDto.class);
        } else {
            throw new ResourceNotFoundException(CourseErrorMessage.courseNotFound(id));
        }
    }
}

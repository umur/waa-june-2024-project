package com.waa.project.service;

import com.waa.project.dto.responses.CourseResponseDTO;
import com.waa.project.entity.Course;

import java.util.List;

public interface CourseService {
    public List<CourseResponseDTO> getAllCourses();
    public CourseResponseDTO getCourseById(Long id);
}

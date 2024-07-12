package com.waa.project.service;

import com.waa.project.dto.responses.CourseResponseDto;

import java.util.List;

public interface CourseService {
    public List<CourseResponseDto> getAllCourses();
    public CourseResponseDto getCourseById(Long id);
}

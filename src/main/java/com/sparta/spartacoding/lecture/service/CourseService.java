package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    public List<CourseResponseDto> getMain() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseResponseDto::new).toList();
    }

    public List<CourseAllResponseDto> getAllPage() {
        return courseRepository.findAll().stream().map(CourseAllResponseDto::new).toList();
    }
}

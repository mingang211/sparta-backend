package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<CourseResponseDto> getMain() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseResponseDto::new).toList();
    }
}
